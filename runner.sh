#!/bin/bash

#-------------------------------------------------------------------
#  This script expects the following environment variables
#     HOST
#     BROWSER
#     RUN
#     XMLFILE
#-------------------------------------------------------------------

# Let's print what we have received
echo "-------------------------------------------"
echo "HUB_HOST      : ${HOST}"
echo "BROWSER       : ${BROWSER:-chrome}"
echo "THREAD_COUNT  : ${RUN:-1}"
echo "TEST_SUITE    : ${XMLFILE}"
echo "-------------------------------------------"

# Do not start the tests immediately. Hub has to be ready with browser nodes
echo "Checking if hub is ready..!"
count=0
while [ "$( curl -s http://${HUB_HOST:-hub}:4444/status | jq -r .value.ready )" != "true" ]
do
  count=$((count+1))
  echo "Attempt: ${count}"
  if [ "$count" -ge 30 ]
  then
      echo "**** HUB IS NOT READY WITHIN 30 SECONDS ****"
      exit 1
  fi
  sleep 1
done

# At this point, selenium grid should be up!
echo "Selenium Grid is up and running. Running the test...."

# Start the java command
java -cp 'libs/*' \
     -Dselenium.grid.enabled=true \
     -Dselenium.grid.hubHost="${HOST}" \
     -Dbrowser="${BROWSER:-chrome}" \
     org.testng.TestNG \
     -parallel tests \
     -threadcount "${RUN:-1}" \
     test-suites/"${XMLFILE}"