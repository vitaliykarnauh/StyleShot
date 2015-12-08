$(document).ready(function (){
	// $('[data-toggle="tooltip"]').tooltip({
    //     placement: 'top'
    // });

	$(".pl-check-btn").prop('disabled', true);
    $(".error-check-btn").prop('disabled', true);
    $("#header").load("html/header.html");
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
            var count = this.files.length;
            console.log(count);
            if (count == 1) {
                $(".pl-check-btn").prop('disabled', true);
                $(".font-t").prop('disabled', false);
				$(".font-s").prop('disabled', false);
                $(".error-check-btn").prop('disabled', false);
            } else if (count > 1) {
                $(".pl-check-btn").prop('disabled', false);
				$(".font-t").prop('disabled', true);
				$(".font-s").prop('disabled', true);
                $(".error-check-btn").prop('disabled', true);
            }
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
		maxFiles: 10
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
	$dz_div.append('<img src="/images/inbox-upload.png" alt="uploading pic" class="uploading-pic" />');
	//$dz_div.append('<img src="images/inbox-upload.png" alt="uploading pic" class="uploading-pic" />');
});