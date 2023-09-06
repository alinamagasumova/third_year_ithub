<?php
$name = '';
$age = '';
if($_SERVER['REQUEST_METHOD'] == 'POST') {
    $name = trim(strip_tags($_POST['name']));
    $age = abs((int)$_POST['age']);
    if($name and $age) {
        echo "Your name: " . $name . "<br>";
        echo "Your age: " . $age . "<br>";
    }
    header('Location: ' . $_SERVER['SCRIPT_NAME']);
    exit;
}
?>
<h1>Single POST</h1>
<form action="<?=$_SERVER['SCRIPT_NAME']?>" method="post">
    <input name='name' value='<?=$name?>'><br>
    <input name='age' value='<?=$age?>'><br>
    <input type='submit'>
</form>