#!/bin/bash

#usage on terminal: sh sync.sh [branch to sync]
#if no branch is specified, master is used as default.

if [[ $1 == "" ]];
then
	echo "Branch to sync defaulted to master."
	BRANCH_TO_SYNC="master"
else
	BRANCH_TO_SYNC=$1
fi

BRANCH_EXIST=$(git branch --list $BRANCH_TO_SYNC)

if [[ -z ${BRANCH_EXIST} ]]; 
then
    echo "Branch to sync does not exist."
    exit
fi

CURRENT_BRANCH=`git branch`

if [[ $CURRENT_BRANCH != *"*	$BRANCH_TO_SYNC"* ]];
then
	echo "Switching to branch master..."
	git checkout master
fi

REMOTE=`git remote -v`

if [[ $REMOTE == *"upstream	https://github.com/AY2021S2-CS2103T-T12-4/tp.git (fetch)"*
    && $REMOTE == *"upstream	https://github.com/AY2021S2-CS2103T-T12-4/tp.git (push)"* ]];
then
    echo "Upstream found..."
else
    echo "Upstream not found"
    echo "Adding upstream..."
    git remote add upstream https://github.com/AY2021S2-CS2103T-T12-4/tp.git
fi

echo "Fetching upstream..."
git fetch upstream
echo "Merging upstream with master..."
git merge upstream/master
echo "Pushing local master to remote repository..."
git push
