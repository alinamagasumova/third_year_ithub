<?php
session_start();
$img = imagecreatefromjpeg("./noise.jpg");
$color = imagecolorallocate($img, 0, 0, 0);
$arr = array(
    'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 
    'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
    '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
);
$kapcha = '';
$x = 15;
for ($i = 0; $i < 5; $i++) {
    $letter = $arr[random_int(0, count($arr) - 1)];
    $kapcha .= $letter;
    if($i==0){
        imagettftext($img, 22, random_int(-15, 15), $x, 30, $color, '/System/Library/Fonts/Supplemental/AmericanTypewriter.ttc', $letter);
    } else {
        imagettftext($img, 22, random_int(-15, 15), $x+=40, 30, $color, '/System/Library/Fonts/Supplemental/AmericanTypewriter.ttc', $letter); 
    }
}

$_SESSION['kapchaCode'] = $kapcha;
imagejpeg($img);