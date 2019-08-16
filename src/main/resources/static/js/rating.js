
var res2 = document.getElementsByName('rating');
var xhr = new XMLHttpRequest();

var id = document.getElementById('book');
id = id.getAttribute("value");
for (var i = 0; i <res2.length ; i++) {
    console.log(res2[i]);
    console.log(i);
    res2[i].addEventListener("click",
        function () {
            var a = res2[0].getAttribute('value');
            xhr.open("PATCH", '/books/' + id + '/rating/'+a, true);
            xhr.send();
            console.log(a);
        }, false
    );
}