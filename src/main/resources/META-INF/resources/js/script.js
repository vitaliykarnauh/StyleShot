$(document).ready(function (){
	'use scrict'


	// $('[data-toggle="tooltip"]').tooltip({
    //     placement: 'top'
    // });

    $("#header").load("header.html");
    $('.error-div').hide();

    $('#file-upload').change(function() {
    	$('#upload-btn').prop('disabled', false);
    	$full_path = $('#file-upload').val();
    	partials = $full_path.split('\\');
		$file_name = partials[partials.length - 1];
		extension = $file_name.split('\.').slice(-1)[0];
		if (extension != 'docx') {
			showExtError();
		} else {
			everythingIsFine($file_name);
		}
    });

    Dropzone.options.filesdropzone = {
		paramName: "file", // The name that will be used to transfer the file
		accept: function(file, done) {
			if (file.name.split('\.').slice(-1)[0] != 'docx') {
				// alert(file.type);
				this.removeFile(file);
				showExtError();
			} else {
				done();
				everythingIsFine();
			}
			// alert(file);
			// extension = file.name.split('\.').slice(-1)[0];
			// if (extension != "docx") {
			// 	showExtError();
			// }
			// else { 
			// 	everythingIsFine();
			// }
		},
		maxFiles: 1
	};
});


function showExtError() {
	// $('#upload-btn').prop('disabled', true);
	// $('.file-upload-name').val('');
	$('.error-div').fadeIn(600);
}

function everythingIsFine($file_name) {
	// $('.file-upload-name').val($file_name);
    $('.error-div').fadeOut(600);
}

$(window).load(function() {
	$dz_div = $('div.dz-default.dz-message');
	$dz_div.find('span').html('Перетащите файл для загрузки');
	//$dz_div.append('<img src="images/inbox-upload.png" alt="uploading pic" class="uploading-pic" />');
	$dz_div.append('<img src="../META-INF/resources/images/inbox-upload.png" alt="uploading pic" class="uploading-pic" />');
	$dz_div.append('<img src="images/inbox-upload.png" alt="uploading pic" class="uploading-pic" />');
});