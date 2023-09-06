<?php
session_start();
$name = '';
$age = '';
if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = trim(strip_tags($_POST['name']));
    $age = abs((int)$_POST['age']);
    $_SESSION['name'] = $name;
    $_SESSION['age'] = $age;
    if($name and $age) {
        echo "Your name: " . $name . "<br>";
        echo "Your age: " . $age . "<br>";
    }
    header('Location: ' . $_SERVER['SCRIPT_NAME']);
    exit;
}
if(isset($_SESSION['name'])) {
    $name = $_SESSION['name'];
}
if(isset($_SESSION['age'])) {
    $age = $_SESSION['age'];
}
?>
<form action="<?=$_SERVER['SCRIPT_NAME']?>" method="post">
    <input name='name' value='<?=$name?>'><br>
    <input name='age' value='<?=$age?>'><br>
    <input type='submit'>
</form>