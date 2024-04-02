function loadGetFactMsg() {
    let nameVar = document.getElementById("factors").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
           document.getElementById("getrespmsg").innerHTML =
           this.responseText;
    };
    xhttp.open("GET", "/factors?value="+nameVar);
    xhttp.send();
}

function loadGetPriMsg() {
    let nameVar = document.getElementById("primes").value;
    const xhttp = new XMLHttpRequest();
    xhttp.onload = function() {
           document.getElementById("getrespmsg").innerHTML =
           this.responseText;
    };
    xhttp.open("GET", "/primes?value="+nameVar);
    xhttp.send();
}