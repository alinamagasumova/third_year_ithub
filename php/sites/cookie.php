<?php
$name = '';
$age = '';
if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = trim(strip_tags($_POST['name']));
    $age = abs((int)$_POST['age']);
    setcookie('name', $name);
    setcookie('age', $age);
    header('Location: ' . $_SERVER['SCRIPT_NAME']);
    exit;
}
// echo $_COOKIE['name'];
if (isset($_COOKIE['name'])) {
    $name = $_COOKIE['name'];
}
if (isset($_COOKIE['age'])) {
    $age = $_COOKIE['age'];
}
?>
<h1>Single POST</h1>
<form action="<?=$_SERVER['SCRIPT_NAME']?>" method="post">
    <input name='name' value='<?=$name?>'><br>
    <input name='age' value='<?=$age?>'><br>
    <input type='submit'>
</form>