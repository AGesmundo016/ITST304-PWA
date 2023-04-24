<?php
include './vendor/autoload.php';
Use Sentiment\Analyzer;
$analyzer = new Analyzer(); 
$text = $analyzer->getSentiment("Tanga");
echo "Position % :". ($text['pos']*100);
echo "<hr/>";
echo "Neutral % :" . ($text['neu']*100);
echo "Position % :". ($text['neg']*100);