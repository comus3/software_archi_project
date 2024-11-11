#!/bin/bash

# Fetch the latest changes from the remote repository
git fetch origin

# Switch to the main branch
git checkout main

# Pull the latest changes from the remote main
git pull origin main

# Get a list of all branches except main
branches=$(git branch | grep -v "main" | sed 's/^[ *]*//')

# Check if any branches were found
if [ -z "$branches" ]; then
    echo "No branches found to merge into."
    exit 0
fi

# Loop through each branch and merge main into them
for branch in $branches; do
    # Checkout the branch
    git checkout "$branch"
    
    # Attempt to merge main into the current branch
    echo "Merging main into $branch..."
    if git merge main; then
        echo "Successfully merged main into $branch."
        
        # Optional: Push the changes to remote
        # git push origin "$branch"
    else
        echo "Merge conflict in branch $branch. Please resolve it manually."
        exit 1
    fi
done

# Switch back to the main branch
git checkout main
