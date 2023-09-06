<?php
// $img = imagecreate(500, 300);
$img = imagecreatetruecolor(500, 300);

$gray = imagecolorallocate($img, 192, 192, 192);
$red = imagecolorallocate($img, 255, 0, 0);
$green = imagecolorallocate($img, 0, 255, 0);
$blue = imagecolorallocate($img, 0, 0, 255);
$black = imagecolorallocate($img, 0, 0, 0);
imagefill($img, 0, 0, $gray); // for png
// imagecolortransparent($img, $green); // for gif

imageantialias($img, true);
imagesetthickness($img, 3);
$style = [$blue, $blue, $blue, $black, $black, $black];
imagesetstyle($img, $style);

imagesetpixel($img, 10, 10, $black);
imageline($img, 20, 20, 80, 280, $red);
imagerectangle($img, 20, 20, 80, 280, IMG_COLOR_STYLED);
// imagefilledrectangle($img, 20, 20, 80, 280, $blue);
$points = [0, 0, 100, 200, 300, 200];
imagepolygon($img, $points, $green);
imageellipse($img, 200, 150, 300, 200, $black);
imagearc($img, 210, 160, 300, 200, 0, 90, $red);
// imagefilledarc($img, 210, 160, 300, 200, 0, 90, $red, IMG_ARC_PIE);
// imagefilledarc($img, 210, 160, 300, 200, 0, 90, $red, IMG_ARC_CHORD);
// imagefilledarc($img, 210, 160, 300, 200, 0, 90, $red, IMG_ARC_NOFILL | IMG_ARC_EDGED);

imagestring($img, 5, 150, 200, 'Hello', $blue); // 1-5 size (5-the biggest)
imagecharup($img, 5, 150, 225, 'Hello', $blue);
imagettftext($img, 30, 10, 300, 150, $green, '/System/Library/Fonts/Supplemental/Academy Engraved LET Fonts.ttf', 'Hello!'); // 10 - угол наклона

// imagegif($img, 'pic.gif');
// imagepng($img, 'pic2.png');
imagepng($img);