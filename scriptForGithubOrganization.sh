#!/bin/bash
USERNAME="ttiimmothy"
TOKEN=""

REPO_URL="https://api.github.com/user/repos?per_page=100"
REPOS=$(curl -s -H "Authorization: Bearer ${TOKEN}" ${REPO_URL} | grep -o '"ssh_url": "git@github.com:openrice-canada[^"]*"' | awk -F'"' '{print $4}')

echo "{\"repository_url\":{" > repository.json
index=1
for repo in ${REPOS}; do
  repo_name=$(basename ${repo})
  echo "\"${index}\": [\"${repo_name}\"]," >> repository.json
  ((index++))
done
echo "}}" >> repository.json

# for repo in ${REPOS}; do
#   repo_name=$(basename ${repo} .git)
#   repo_name_with_underscore=${repo_name//-/_}
#   git clone "${repo}" "${repo_name_with_underscore}"
# done

# for repo in ${REPOS}; do
#   repo_name=$(basename ${repo} .git)
#   repo_name_with_underscore=${repo_name//-/_}
#   echo "${repo_name_with_underscore}"
# done
