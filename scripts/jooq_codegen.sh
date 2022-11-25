#!/bin/bash
set -e
cd "${0%/*}/.."

function _echo() {
  echo -e '\n------------------------------------------'
  echo "$@"
  echo -e '------------------------------------------\n'
}

###########################################################
# Pre-install commands:
#
# 1. Install 'jbang' by SDKMAN
#    https://www.jbang.dev/documentation/guide/latest/installation.html#sdkman
#
# 2. Install 'psql' by homebrew (Optional)
#    brew install libpq
#
###########################################################

# show the latest flyway_schema_history records by psql
if [[ -n "$(type -p psql)" ]]; then
  psql -h localhost -p 5700 -U postgres -c "
  select type, script, installed_by, installed_on, execution_time, success
  from spring_demo.flyway_schema_history
  order by installed_rank
  desc limit 10"

  printf "Do you want to generate the code by jooq based on the flyway records above? [Y/N] "
else
  echo "'psql' command is not found. Skip showing the latest flyway_schema_history records."
  printf "\nDo you want to generate the code by jooq? [Y/N] "
fi

# read the answer from user
read -r ans
if [[ $ans != "Y" && $ans != "y" ]]; then
  exit
fi

_echo "Generate the jooq package"
jbang -Djooq.version=3.15.12 --deps org.postgresql:postgresql:42.4.1 codegen@jooq src/main/resources/jooq-config.xml

_echo "Format the code by prettier"
mvn prettier:write

_echo "git add jooq package"
git add src/main/java/com/zlargon/springdemo/jooq
