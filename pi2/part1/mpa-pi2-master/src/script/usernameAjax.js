const ajax = new XMLHttpRequest();
const method = "GET";
let url = "../DAO/request_ajax_data.php?username=";
const async = true;


const checkUsername = (username) => {

    url = url.concat(username); 
    ajax.onreadystatechange = function() {
        if(this.readyState == 4 && this.status === 200) {
            if(this.response) {
                alert("Já existe um Utilizador ")
            }
        }
    }
    ajax.open(method, url);
    ajax.send();
    url = "../DAO/request_ajax_data.php?username=";
}


