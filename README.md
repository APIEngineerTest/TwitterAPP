# TwitterApp (Twitter API implementation)
TwitterAPI Application

###Description
This project is a client which is uses Twitter API. <br>
The purpose of the project is to learn and implement the Twitter API. <br>
The main focus in this project is on the back-end implementation using RESTful webservices. <br>
This app has been developed using Java, Twitter API, RESTful Webservices.<br>

####The following are the features for this app.<br>
1.	Accepts a twitter handle and return the last X posts by that person.<br>
This supports pagination as well.<br>
2.	We enter two twitter handles and return the list of their shared friends.<br>
This has pagination functionality.<br>
##Steps to run the app:
1.	$git clone https://github.com/APIEngineerTest/TwitterApp<br>
2.	$cd TwitterApp<br>
3.	Import the project using eclipse.<br>
4.  Add the following jars to your application using build path.<br>
    a.	Commons-io-1.3.2.jar<br>
    b.	Commons-logging-1.1.1.jar<br>
    c.	Httpcore-4.0-beta3.jar<br>
    d.	Json-20160212.jar<br>
    e.	Oauth-consumer.jar<br>
    f.	Signpost-commonshttp4-1.2.jar<br>
    g.	Signpost-core-1.2.jar<br>
    h.	Commons-codec-1.3.jar<br>
    i.	Httpclient-4.0.1.jar<br>
5.	Once the jar files have been added, please replace the AccessToken, AccessSecret, ConsumerKey, ConsumerSecret values with your generated API keys.<br>
6.	Please run the application using run command in Eclipse.<br>


###Instructions to check the functionality:
1.	Feature1UserTweets.java<br>
Open the Feature1UserTweets.java in a window in Eclipse and run the java file using run button in eclipse.<br>
The following messages appears on the console.<br>
A.	Enter valid User Name of the twitter account: <br>
Example, enter this as input : SwathyDamera<br>
B.	Click on Enter<br>
C.	Enter last 'x' posts of SwathyDamera you want to view.<br>
Example, enter this as input : 3<br>
D.	Click on enter<br>
E.	This displays, the most recent 3 posts of user, SwathyDamera, on the console.<br>
The following are the instructions to check the pagination feature.<br>
F.	Once you have executed the above instructions from 1-5, we can see a message on the console saying,  “please enter F or f for forward , any key for backward x for exit.” <br>
Case 1: backward navigation <br>
G.	Enter b <br>
Click on Enter <br>
H.	Then it informs the user “You are not allowed to move back at this point please enter F or f for forward , x for exit.  !!!” Since it’s the first page.<br>
Case 2: Forward Navigation <br>
a.	Enter ‘f’ <br>
Click on Enter<br>
b.	It displays the next 3 tweets of the user <br>
c.	If you want to keep seeing the next tweets, then we need to keep entering ‘f’ i.e. step a. until we want to see all the tweets of the user.
So the pagination feature is achieved / displayed using the above instructions.<br>

2.	Feature2SharedFriends.java<br>
Open the Feature2SharedFriends.java in a window in Eclipse and run the java file using run button in eclipse.<br>
The following messages appears on the console.<br>
a.	Enter the Screen Name of the first Twitter User:<br>
Enter “PVSindhu” for example<br>
b.	Click on Enter<br>
c.	Enter the Screen Name of the second Twitter User:<br>
Enter “PVSindhu” for example<br>
d.	Click on Enter<br>
The number of shared friends would be displayed on the console with this message:<br>
No of shared friends: 61<br>
And we list the first 10 shared friends on the console (imagining it to be the first page)<br>
Instructions for testing the pagination:<br>
Once you have executed the above instructions from a-d, we can see a message on the console saying,  “please enter F or f for forward , any key for backward x for exit.”<br>
Case 1: backward navigation <br>
a.	Enter b  <br>
Click on Enter <br>
Then it informs the user “You are not allowed to move back at this point please enter F or f for forward , x for exit.  !!!” Since it’s the first page.<br>
Case 2: Forward Navigation<br>
a.	Enter ‘f’<br>
Click on Enter<br>
b.	It displays the next 10 shared friends of the users <br>
c.	If you want to keep seeing the next set of shared friends, then we need to keep entering ‘f’ i.e. step a. until we want to see all the shared friends of the user.<br>
So the pagination feature is achieved / displayed using the above instructions.<br>
