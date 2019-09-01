#!/usr/bin/env bash

function parse_git_branch {
    git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ \1/'
}

readonly PARENT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
readonly CURRENT_BRANCH=$(parse_git_branch)
readonly BRANCH_TO_UPDATE="quick_storing"

if [ ${CURRENT_BRANCH} != ${BRANCH_TO_UPDATE} ]; then
    echo "Not the right branch to store data"
    git fetch -a
    git checkout ${BRANCH_TO_UPDATE}
fi

bash ${PARENT_DIR}/extract_data.sh
git add ${PARENT_DIR}/../data/url_bookmarks.sql
git commit -m "update data"
git push -f origin ${BRANCH_TO_UPDATE}
git checkout ${CURRENT_BRANCH}
