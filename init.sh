#!/bin/zsh

cd $(dirname $0) || exit

echo -n "Enter the project name > " && read PROJECT_NAME
echo -n "Enter the git repository url > " && read GIT_REPOSITORY_URL
PROJECT_NAME_UPPER_CASE=${PROJECT_NAME:u}
PROJECT_NAME_LOWER_CASE=${PROJECT_NAME:l}

mv src/main/kotlin/com/it_finne/discordbot_template_for_kotlin src/main/kotlin/com/it_finne/"$PROJECT_NAME_LOWER_CASE"

grep -rl discordbot_template_for_kotlin ./src/main/kotlin/com/it_finne/"$PROJECT_NAME_LOWER_CASE" | xargs sed -i "" "s/discordbot_template_for_kotlin/$PROJECT_NAME_LOWER_CASE/g"
sed -i "" "s/{PROJECT_NAME_UPPER_CASE}/$PROJECT_NAME_UPPER_CASE/g" ./build.gradle.kts
sed -i "" "s/{PROJECT_NAME_LOWER_CASE}/$PROJECT_NAME_LOWER_CASE/g" ./build.gradle.kts
sed -i "" "s/{PROJECT_NAME_LOWER_CASE}/$PROJECT_NAME_LOWER_CASE/g" ./settings.gradle.kts

git remote set-url origin "$GIT_REPOSITORY_URL"

cp ./src/main/resources/application.yaml.sample ./src/main/resources/application-development.yaml
cp ./src/main/resources/application.yaml.sample ./src/main/resources/application-staging.yaml
cp ./src/main/resources/application.yaml.sample ./src/main/resources/application-production.yaml

rm ./init.sh

cd ../
mv discordbot-template-for-kotlin "$PROJECT_NAME_LOWER_CASE"
