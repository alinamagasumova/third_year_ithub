<?php
echo "ЧЕГО СКАЗАТЬ-ТО ХОТЕЛ, МИЛОК?!\n";
$line = readline();
$bye = 0;
while (true) {
    if ($line == strtoupper($line) and substr($line, -1) == "!") {
        if ($line == "ПОКА!") {
            $bye += 1;
            if ($bye == 3) {
                echo "ДО СВИДАНИЯ, МИЛЫЙ!\n";
                break;
            }
        } else {$bye = 0;}
        echo "НЕТ, НИ РАЗУ С " . rand(1930, 1950) . " ГОДА!\n";
        $line = readline();
    }
    else {
        $bye = 0;
        echo "АСЬ?! ГОВОРИ ГРОМЧЕ, ВНУЧЕК!\n";
        $line = readline();
    }
}
