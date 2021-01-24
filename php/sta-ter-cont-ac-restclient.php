<?php

// constants
const PATH_TO_CLASSES = ".class";

// user classes autoloading
spl_autoload_register(function($class_name)
{
  $class_source_file_path = PATH_TO_CLASSES."/".$class_name.".php";
  require_once $class_source_file_path;
});

$requestJson = "{ \"method_number\":3, \"owner\":\"francie\", \"continent\":\"tichý oceán\" }";
$replyJson = tWeb::request($requestJson);
echo $replyJson.PHP_EOL;

?>
