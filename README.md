# Pre-work - *TODO Application*

**TODO Application** is an android app that allows building a todo list and basic todo items management functionality including adding new items, editing and deleting an existing item.

Submitted by: **Greeshma Umapathi**

Time spent: **10** hours spent in total

## User Stories

The following **required** functionality is completed:

* [x] User can **successfully add and remove items** from the todo list
* [x] User can **tap a todo item in the list and bring up an edit screen for the todo item** and then have any changes to the text reflected in the todo list.
* [x] User can **persist todo items** and retrieve them properly on app restart

The following **optional** features are implemented:

* [x] Persist the todo items [into SQLite](http://guides.codepath.com/android/Persisting-Data-to-the-Device#sqlite) instead of a text file
* [x] Improve style of the todo items in the list [using a custom adapter](http://guides.codepath.com/android/Using-an-ArrayAdapter-with-ListView)
* [x] Add support for completion due dates for todo items (and display within listview item)
* [ ] Use a [DialogFragment](http://guides.codepath.com/android/Using-DialogFragment) instead of new Activity for editing items
* [x] Add support for selecting the priority of each todo item (and display in listview item)
* [x] Tweak the style improving the UI / UX, play with colors, images or backgrounds

The following **additional** features are implemented:

* [x] List anything else that you can get done to improve the app functionality!

- Sorting Items in ListView based on priority.
- Strike-out the already done items

## Video Walkthrough
**With Notification**
Here's a walkthrough of implemented user stories:
**Initial Submission**
<img src='http://i.imgur.com/OBeMD99.gif' title='With notification' width='' alt='Video Walkthrough' />

<img src='http://i.imgur.com/b2oAcfS.gif' title='Initial submission' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Project Analysis

As part of your pre-work submission, please reflect on the app and answer the following questions below:

**Question 1:** "What are your reactions to the Android app development platform so far? Compare and contrast Android's approach to layouts and user interfaces in past platforms you've used."

**Answer:** In contrast to C# app development, the separation of UI components/View in the layout and the logic/Controller in the Activity presents a very clean framework for development. 
When using the Android, there is a tendency to mix the behavior of a UI component with the look-and-feel of it, where as when doing web developments, the UI related code is separated in style sheet files and javascript would handle the behavior. 
Drupal uses module and include files, which is analogous to Activity files in Android. In both cases these tend to get huge and be logic intensive.

**Question 2:** "Take a moment to reflect on the `ArrayAdapter` used in your pre-work. How would you describe an adapter in this context and what is its function in Android? Why do you think the adapter is important? Explain the purpose of the `convertView` in the `getView` method of the `ArrayAdapter`."

**Answer:** `ArrayAdapter` “ties together” the data-object/string to the UI component. When the state of the data source is changed, it is the `ArrayAdapter` that responds to the change and ensures that the UI component adapts to the change in the data. 
`convertView` is used to re-use any old view. The `ArrayAdapter` populates the items in the View by calling the `getView` method. When the List is scrolled, the `convertView` checks to see if there are any existing “shells” that are no longer being used; and reuses these to attach the current items to the View. This way only the items currently displayed on the screen are in-memory and in the View list.


## Notes

Describe any challenges encountered while building the app.

## License

    Copyright [2017] Greeshma Umapathi

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
