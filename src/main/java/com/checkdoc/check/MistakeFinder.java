package com.checkdoc.check;

import com.checkdoc.domain.Document;
import com.checkdoc.domain.Mistake;
import com.checkdoc.domain.MistakeType;
import com.checkdoc.service.MistakeService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTPageMar;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;


/**
 * @author Oleg Dashkevych
 * @version 1.0 4.11.2015
 */
public class MistakeFinder {

	private List<Mistake> mistakes;

	private Document document;

	private Rule rule;

	private MistakeTypeFactory factory;

	private MistakeService mistakeService;

	/**
	 * @param rule     Rules to check document
	 * @param document Document to check
	 */
	public MistakeFinder(Document document, Rule rule, MistakeService mistakeService) {
		this.document = document;
		this.rule = rule;
		factory = new MistakeTypeFactory();
		this.mistakeService = mistakeService;
		mistakes = new ArrayList<>();
	}

	/**
	 * Checks document on mistakes.
	 * It includes other methods, each of them checks according to rule
	 *
	 * @return List of mistakes in this call of check method
	 */
	public List<Mistake> check() {
		File directory;
		mistakes = new ArrayList<>();
		FileInputStream fis = null;
		XWPFDocument documentDocx;
		XWPFParagraph paragraph;
		List<XWPFParagraph> paraList;
		Iterator<XWPFParagraph> paraIter;
		/** Path of file to check*/
		String savePath = document.getDirectory().getUrl();
		savePath += "/" + document.getUrl();
		try {
			directory = new File(savePath);
			if (!directory.exists()) {
				directory.mkdirs();
			}
			File[] files = directory.listFiles();
			if (files != null) {
				fis = new FileInputStream(files[0]);
			}

			if (fis != null) {
				documentDocx = new XWPFDocument(fis);
				paraList = documentDocx.getParagraphs();
				paraIter = paraList.iterator();

				/** Here we add methods with logic of check*/
				checkIntendSize(documentDocx);

				while (paraIter.hasNext()) {
					paragraph = paraIter.next();
					List<XWPFRun> runsList = paragraph.getRuns();
					// iterate through runs
					for (XWPFRun run : runsList) {

						/** Here we add methods with logic of check*/
						checkFont(run);
						checkFontSize(run);
					}
				}
				fis.close();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return mistakes;
	}

	/**
	 * Checks font, designated in rule
	 * and ADDS mistake to DB
	 *
	 * @param sentences Paragraph of document
	 */
	private void checkFont(XWPFRun sentences) {
		String font = sentences.getFontFamily();
		if (!font.equals(rule.getFont())) {
			MistakeType type = factory.getMistakeType(MistakeTypeEnum.FONT_MISTAKE);
			Mistake mistake = new Mistake(sentences.toString(), document, type);
			mistakeService.add(mistake);
			mistakes.add(mistake);
		}
	}

	/**
	 * Checks font size, designated in rule
	 *
	 * @param sentences Paragraph of document
	 */
	private void checkFontSize(XWPFRun sentences) {
		if (sentences != null) {
			int size = sentences.getFontSize();
			if (size != rule.getFontSize()) {
				MistakeType type = factory.getMistakeType(MistakeTypeEnum.FONT_SIZE_MISTAKE);
				Mistake mistake = new Mistake(sentences.toString(), document, type);
				mistakeService.add(mistake);
				mistakes.add(mistake);
			}
		}
	}

	/**
	 * Checks intend size, designated in rule
	 *
	 * @param documentDocx document
	 */
	private void checkIntendSize(XWPFDocument documentDocx) {
		if (documentDocx != null) {
			CTPageMar sectPr = documentDocx.getDocument().getBody().getSectPr().getPgMar();
			BigInteger intendLeft = sectPr.getLeft();
			if (intendLeft != null) {
				if (!Objects.equals(intendLeft, BigInteger.valueOf(rule.getIntendSize()))) {
					MistakeType type = factory.getMistakeType(MistakeTypeEnum.INTEND_SIZE_MISTAKE);
					Mistake mistake = new Mistake(null, document, type);
					mistakeService.add(mistake);
					mistakes.add(mistake);
				}
			}
		}
	}
}
