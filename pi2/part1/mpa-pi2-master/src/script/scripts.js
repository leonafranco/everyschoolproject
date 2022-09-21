
const send_to_login = () =>{
    window.location.href = "src/View/login.php";
}

const send_to_login_from_register = () =>{
  window.location.href = "login.php";
}

const send_to_register = () =>{
    window.location.href = "src/View/register.php";
}

const send_to_settings = () => {
  window.location.href = "settings.php";
}

const deleteCategory = () => {
  var x = document.getElementById("myBtn").value;
  console.log(x);
}

