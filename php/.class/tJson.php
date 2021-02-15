<?php

class tJson
{
  const METHOD_NUMBER = "method_number";
  const COUNTRY = "country";
  const ISO_CODE_2_CHAR = "iso_code_2_char";
  const ISO_CODE_3_CHAR = "iso_code_3_char";
  const OWNER = "owner";
  const CONTINENT = "continent";
  const PHONE_PREFIX = "phone_prefix";

  const ERROR_CODE = "error_code";
  const ERROR_STRING = "error_string";
  const DATA = "data";

  const METHOD_OWNERS_NUMBER = 1;
  const METHOD_CONTINENTS_NUMBER = 2;
  const METHOD_ONE_QUERY_NUMBER = 3;

  protected static $lastErrorString;

  //----------------------------------------------------------------------------
  public static function codeQueryOwners()
  {
    $output[self::METHOD_NUMBER] = self::METHOD_OWNERS_NUMBER;

    $outputJson = json_encode($output);

    return $outputJson;
  }
  //----------------------------------------------------------------------------
  public static function codeQueryContinents()
  {
    $output[self::METHOD_NUMBER] = self::METHOD_CONTINENTS_NUMBER;

    $outputJson = json_encode($output);

    return $outputJson;
  }
  //----------------------------------------------------------------------------
  public static function codeQueryOneQuery(&$country, &$iso_code_2_char, &$iso_code_3_char, &$owner, &$continent, &$phone_prefix)
  {
    $output[self::METHOD_NUMBER] = self::METHOD_ONE_QUERY_NUMBER;
    $output[self::COUNTRY] = $country;
    $output[self::ISO_CODE_2_CHAR] = $iso_code_2_char;
    $output[self::ISO_CODE_3_CHAR] = $iso_code_3_char;
    $output[self::OWNER] = $owner;
    $output[self::CONTINENT] = $continent;
    $output[self::PHONE_PREFIX] = $phone_prefix;

    $outputJson = json_encode($output);

    return $outputJson;
  }
  //----------------------------------------------------------------------------
  public static function decodeResult(&$inputJson)
  {
    $retData = null;
    self::$lastErrorString = null;

    $input = json_decode($inputJson, true);

    if ((!isset($input[self::ERROR_CODE])) or (!isset($input[self::ERROR_STRING])))
    {
      // invalid JSON
      $retData = null;
    }
    elseif ($input[self::ERROR_CODE] !== 0)
    {
      // error reported by the service
      $retData = null;
      self::$lastErrorString = $input[self::ERROR_STRING];
    }
    elseif ((!isset($input[self::DATA])) or (!is_array($input[self::DATA])))
    {
      // corrupted JSON
      $retData = null;
    }
    else
    {
      $retData = $input[self::DATA];
    }

    return $retData;
  }
  //----------------------------------------------------------------------------

  //----------------------------------------------------------------------------
  public static function getLastErrorString()
  {
    return self::$lastErrorString;
  }
  //----------------------------------------------------------------------------
}

?>
