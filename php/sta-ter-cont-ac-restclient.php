<?php

// constants
const PATH_TO_CLASSES = ".class";

// user classes autoloading
spl_autoload_register(function($class_name)
{
  $class_source_file_path = PATH_TO_CLASSES."/".$class_name.".php";
  require_once $class_source_file_path;
});

// all continents query
echo "All continents:".PHP_EOL;

$requestJsonContinents = tJson::codeQueryContinents();

$replyJsonContinents = tWeb::request($requestJsonContinents);
if ($replyJsonContinents === null)
{
  echo " - error sending POST web query".PHP_EOL;
  return;
}

$continents = tJson::decodeResultOwnersOrContinents($replyJsonContinents);
if ($continents === null)
{
  echo " - error decoding of reply JSON".PHP_EOL;
  return;
}

foreach ($continents as $continent => $count)
{
  echo " - ".$continent." (".$count." countries}".PHP_EOL; 
}

echo PHP_EOL;

// one query
echo "One query:".PHP_EOL;

$country = "";
$iso_code_2_char = "";
$iso_code_3_char = "";
$owner = "francie";
$continent = "tichý oceán";
$phone_prefix = "";

$requestJson = tJson::codeQueryOneQuery($country, $iso_code_2_char, $iso_code_3_char, $owner, $continent, $phone_prefix);

$replyJson = tWeb::request($requestJson);
if ($replyJson === null)
{
  echo " - error sending POST web query".PHP_EOL;
  return;
}

$replyItems = tJson::decodeResultOneQuery($replyJson);
if ($replyItems === null)
{
  echo " - error decoding of reply JSON".PHP_EOL;
  return;
}

foreach ($replyItems as $replyItem)
{
  echo " - country: ".$replyItem[tJson::COUNTRY].PHP_EOL;
  echo " - ISO code 2 characters: ".$replyItem[tJson::ISO_CODE_2_CHAR].PHP_EOL;
  echo " - ISO code 3 characters: ".$replyItem[tJson::ISO_CODE_3_CHAR].PHP_EOL;
  echo " - owner: ".$replyItem[tJson::OWNER].PHP_EOL;
  echo " - continent: ".$replyItem[tJson::CONTINENT].PHP_EOL;
  echo " - phone prefix: ".$replyItem[tJson::PHONE_PREFIX].PHP_EOL;

  echo PHP_EOL;
}

?>
