#!/usr/bin/env bash

function parse_git_branch {
    git branch 2> /dev/null | sed -e '/^[^*]/d' -e 's/* \(.*\)/ \1/'
}

readonly PARENT_DIR=$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )
readonly CURRENT_BRANCH=$(parse_git_branch)
readonly BRANCH_TO_UPDATE_FROM="quick_storing"

if [ ${CURRENT_BRANCH} != ${BRANCH_TO_UPDATE_FROM} ]; then
    echo "Not the right branch to update data from"
    git fetch -a
    git checkout ${BRANCH_TO_UPDATE_FROM}
fi

git pull origin ${BRANCH_TO_UPDATE_FROM}
bash ${PARENT_DIR}/restore_data.sh
git checkout ${CURRENT_BRANCH}
