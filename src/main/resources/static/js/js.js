// document.addEventListener('DOMContentLoaded',function(){
$(document).ready(function(){

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();

            reader.onload = function (e) {
                $('#blah').attr('src', e.target.result);
                var img = document.getElementById('cancel');
                img.style.display = 'inline';
            }

            reader.readAsDataURL(input.files[0]);
        }
    }

    $("#picture").change(function(){
        console.log("console");
        readURL(this);

    });
});


