# Jane POS Android Interview Session

Thank you for choosing to interview at Jane!  
This outlines the info to complete Jane's Android Take-Home project: Refactoring an Android
application

Good Luck!

## Description

The application should display a list of star wars characters, fetched from this 
[open API](https://github.com/akabab/starwars-api). Clicking a (https://akabab.github.io/starwars-api/api/all.json) https://akabab.github.io/starwars-api/api/id/1.json
character should display a profile with additional information.

There may be crashes and/or bugs in the current code. We want you to refactor the app to kotlin,
using your best judgement on how to resolve any issues you encounter.

We are looking for real world code, so utilize best practices for your architecture and testability
choices. You are free to change the UI/UX and use any open source libraries you think would be
beneficial.

Tests should be included, we have preference for unit tests. So if time is a concern, focus on these
over UI or snapshot tests. We don't expect 100% code coverage, use your judgement on what to test.

We want to see what "production" ready mobile code looks like to you but we also understand your
time is valuable, so if there's some changes you would have made with more time, let us know!

## Details

We would like for you to make a new network request to the `/id` endpoint for the profile activity.  
In addition to this requirement and the refactor, implement 2 additional features. You are also welcome to add a feature of your choosing rather than from this list.

1. Offline Support
2. "Favorite" a character and pin them to the top of the list
3. Add search to filter characters by name
4. UI indicator if device has current network connection(WiFi or cellular)
5. Way to refresh the list, it can be a button, swipe-refresh, etc.

There is no "right" answer, but here's some libraries
we're currently using if you want to include them

- Viewbinding
- Retrofit
- Coroutines
- Moshi
- GSON

# FAQ

**Q.) Do I need to worry about device size?**  
        A.) No, you can focus on tablet or phone. Just let us know which one you chose

**Q.) Can I use Compose?**  
        A.) Sure! We currently don't use Compose but understand that Android development is quickly adopting it. 

**Q.) Does UI design matter?**  
        A.) We won't evaluate you on your design skills, if you want to keep the current design then 
        that's fine but if you want to show off your design skills, please do!

