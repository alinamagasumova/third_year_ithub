<h1>Регистрация</h1>
<img src="kapchaImage.php">
<form action="<?=$_SERVER['SCRIPT_NAME']?>" method="post">
    <label>Введите код <input type="text" name="kapchaText" maxlength="6"></label><br>
    <button type="submit">OK</button>
</form>
<?php
session_start();
if($_SERVER['REQUEST_METHOD'] == 'POST') {
    if($_SESSION['kapchaCode'] == strtoupper($_POST['kapchaText'])){
        echo "<p>Правильно</p>";
    } else {
        echo "<p>Неправильно. Попробуйте еще раз.</p>";
    }
}