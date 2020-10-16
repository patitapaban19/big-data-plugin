/*!
 * Copyright 2010 - 2020 Hitachi Vantara.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.pentaho.amazon.s3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class S3Util {

  /** System property name for the AWS Endpoint (This is for Minio) */
  public static final String ENDPOINT_SYSTEM_PROPERTY = "aws.endpoint";

  /** System property name for the AWS Signature version (This is for Minio) */
  public static final String SIGNATURE_VERSION_SYSTEM_PROPERTY = "aws.endpoint";

  /** System property name for the AWS access key ID */
  public static final String ACCESS_KEY_SYSTEM_PROPERTY = "aws.accessKeyId";

  /** System property name for the AWS secret key */
  public static final String SECRET_KEY_SYSTEM_PROPERTY = "aws.secretKey";

  /** Environment variable for the AWS region */
  public static final String AWS_REGION = "AWS_REGION";

  /** Environment variable for the specific location of the AWS config file */
  public static final String AWS_CONFIG_FILE = "AWS_CONFIG_FILE";

  /** AWS configuration folder */
  public static final String AWS_FOLDER = ".aws";

  /** Configuration file name */
  public static final String CONFIG_FILE = "config";

  /** Regex for detecting S3 credentials in URI */
  public static final String URI_AWS_CREDENTIALS_REGEX = "(?<=s3[a-z]:\\/\\/).*:.*(?=@s3[a-z]?)";

  /** Regex for detecting File Name parsing with S3 credentials */
  public static final String URI_AWS_CREDENTIALS_FILE_NAME_PARSER_REGEX = "(?<=\\/).*:.*(?=s3[a-z]?)";

  public static boolean hasChanged( String previousValue, String currentValue ) {
    if ( !isEmpty( previousValue ) && isEmpty( currentValue ) ) {
      return true;
    }
    if ( isEmpty( previousValue ) && !isEmpty( currentValue ) ) {
      return true;
    }
    return !isEmpty( previousValue ) && !isEmpty( currentValue ) && !currentValue.equals( previousValue );
  }

  public static boolean isEmpty( String value ) {
    return value == null || value.length() == 0;
  }

  public static String getKeysFromURI( String uri, String regex ) {
    Pattern uriPatternWithCredentials = Pattern.compile( regex );
    Matcher match = uriPatternWithCredentials.matcher( uri );
    String keys = "";
    if ( match.find() ) {
      keys = match.group();
    }
    return keys;
  }

  private S3Util() { }

}
