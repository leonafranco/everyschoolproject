<?php
namespace src\Include;

class Mail {

     function sendMail($email, $username) {
        $to = $email;
        $urlToUser = "http://localhost:8082/src/Include/activate.php?username="."$username";

        $subject = "Ativa a sua conta!";

        $message = "Click no próximo link para ativar a sua conta " . $urlToUser;

        $headers = 'From: The Sender Name <leonardomf96@gmail.com>' . "\r\n" .
        'Reply-To: <leonardomf96@gmail.com>'. "\r\n" .
        'X-Mailer: PHP/' . phpversion();

        $result = mail($to, $subject, $message, $headers);


        return $result;

    }
 }

?>