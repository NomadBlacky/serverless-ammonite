function hello () {
  EVENT_DATA=$1
  echo "$EVENT_DATA" 1>&2;

  JAVA_VERSION=$(java -version 2>&1 | tr "\n" "|" | tr "\"" "'")
  echo "$JAVA_VERSION" 1>&2;

  AMMONITE_STDOUT=$(./amm --home /tmp -c 'println("Hello Ammonite in AWS Lambda!")')
  
  RESPONSE="{\"body\": {\"input\": $EVENT_DATA, \"msg\": \"Wecome to serverless!\", \"java_version\": \"$JAVA_VERSION\", \"amm\": \"$AMMONITE_STDOUT\"}}"

  echo $RESPONSE
}
