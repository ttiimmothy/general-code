#!/bin/bash
USERNAME="ttiimmothy"
TOKEN=process.env.TOKEN

index=1
# echo "{\"repository_url\":{" > $HOME/developer/github_repository/repository.json
for page in {1..2}; do
  REPO_URL="https://api.github.com/user/repos?per_page=100&page=${page}"
  # REPOS=$(curl -H "Authorization: Bearer ${TOKEN}" ${REPO_URL} | grep -o '"ssh_url": "git@github.com:ttiimmothy[^"]*"' | awk -F'"' '{print $4}')
  REPOS=$(curl -s -H "Authorization: Bearer ${TOKEN}" ${REPO_URL} | grep -o '"ssh_url": "git@github.com:ttiimmothy[^"]*"' | awk -F'"' '{print $4}')
  # REPOS=$(curl -s -H "Authorization: Bearer ${TOKEN}" ${REPO_URL} | grep -o '"ssh_url": "git@github.com:ttiimmothy[^"]*"' | awk -F'"' '!/manga-and-comics/ && !/stable-diffusion/ && !/porn-videos/{print $4}')
  # REPOS=$(curl -s -H "Authorization: Bearer ${TOKEN}" ${REPO_URL} | grep -o '"ssh_url": "git@github.com:ttiimmothy[^"]*"' | awk -F'"' '!/manga-and-comics/{print}')
  # REPOS=$(curl -s -H "Authorization: Bearer ${TOKEN}" ${REPO_URL} | grep -o '"ssh_url": "git@github.com:ttiimmothy[^"]*"' | awk -F'"' '{print}')
  # REPOS=$(curl -s -H "Authorization: Bearer ${TOKEN}" ${REPO_URL} | grep -o '"ssh_url": "[^"]*"' | awk -F'"' '!/manga-and-comics/{print}')
  # echo "${REPOS}"

  # for repo in ${REPOS}; do
  #   repo_name=$(basename ${repo})
  #   echo "\"${index}\": [\"${repo_name}\"]," >> $HOME/developer/github_repository/repository.json
  #   ((index++))
  # done

  # for repo in ${REPOS}; do
  #   repo_name=$(basename ${repo} .git)
  #   repo_name_with_underscore=${repo_name//-/_}
  #   git clone "${repo}" "$HOME/developer/github_repository/${repo_name_with_underscore}"
  # done

  for repo in ${REPOS}; do
    repo_name=$(basename ${repo} .git)
    repo_name_with_underscore=${repo_name//-/_}
    echo "${index} ${repo_name_with_underscore}"
    ((index++))
  done
done
# echo "}}" >> $HOME/developer/github_repository/repository.json

# important cmd
# history | awk '{print $2}' | sort | uniq -c | sort -nr | head -10
