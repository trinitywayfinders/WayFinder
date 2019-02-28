# TrinityWayFinders
Source Code Repo for ASE Project

# Code Quality Status
|Service|Badges|
|-------|------|
|Authentication Server| [![Quality Gate Status](http://35.242.176.105/api/project_badges/measure?project=com.wayfinder%3AAuthenticationServer&metric=alert_status)](http://35.242.176.105/dashboard?id=com.wayfinder%3AAuthenticationServer)[![Bugs](http://35.242.176.105/api/project_badges/measure?project=com.wayfinder%3AAuthenticationServer&metric=bugs)](http://35.242.176.105/dashboard?id=com.wayfinder%3AAuthenticationServer)[![Code Smells](http://35.242.176.105/api/project_badges/measure?project=com.wayfinder%3AAuthenticationServer&metric=code_smells)](http://35.242.176.105/dashboard?id=com.wayfinder%3AAuthenticationServer)[![Technical Debt](http://35.242.176.105/api/project_badges/measure?project=com.wayfinder%3AAuthenticationServer&metric=sqale_index)](http://35.242.176.105/dashboard?id=com.wayfinder%3AAuthenticationServer)[![Security Rating](http://35.242.176.105/api/project_badges/measure?project=com.wayfinder%3AAuthenticationServer&metric=security_rating)](http://35.242.176.105/dashboard?id=com.wayfinder%3AAuthenticationServer)[![Reliability Rating](http://35.242.176.105/api/project_badges/measure?project=com.wayfinder%3AAuthenticationServer&metric=reliability_rating)](http://35.242.176.105/dashboard?id=com.wayfinder%3AAuthenticationServer)|
|HL API| [![Quality Gate Status](http://35.242.176.105/api/project_badges/measure?project=HighLevelAPI&metric=alert_status)](http://35.242.176.105/dashboard?id=HighLevelAPI)[![Bugs](http://35.242.176.105/api/project_badges/measure?project=HighLevelAPI&metric=bugs)](http://35.242.176.105/dashboard?id=HighLevelAPI)[![Code Smells](http://35.242.176.105/api/project_badges/measure?project=HighLevelAPI&metric=code_smells)](http://35.242.176.105/dashboard?id=HighLevelAPI)[![Technical Debt](http://35.242.176.105/api/project_badges/measure?project=HighLevelAPI&metric=sqale_index)](http://35.242.176.105/dashboard?id=HighLevelAPI)[![Security Rating](http://35.242.176.105/api/project_badges/measure?project=HighLevelAPI&metric=security_rating)](http://35.242.176.105/dashboard?id=HighLevelAPI)[![Reliability Rating](http://35.242.176.105/api/project_badges/measure?project=HighLevelAPI&metric=reliability_rating)](http://35.242.176.105/dashboard?id=HighLevelAPI)|

# Team ID
Group 3

# Team Members:
Arun Thundyill Saseendran
Saad Tariq Malik
Zihan Huang
Nicholas bonello
Yifan Xu
Mark Grennan

# Base Requirement:
## Project 1: Dynamic Sustainable Wayfinding

The goal of this project is to provide real-time wayfinding for travellers that improves the reliability of the mobility experience across all transportation modes. It will build intelligence at the edges of the network (i.e., decentralised), while providing real-time decision-making and decision support to very large numbers of travellers, across a wide range of transportation modes (including pedestrian, bike, car, bus, tram, train and taxi) tailored to their individual priorities (pollution avoidance, emissions reduction, speed, reliability, comfort etc.). A single trip may involve multiple modes of transport, which may be modified en-route, given changes in the environment, e.g., congestion, noise pollution, crowded and so on.

Some of the main challenges include identifying when a user’s interests are likely to conflict with sustainability goals, how a user can be incentivised to choose a more sustainable travel plan, and when to trigger an adaptation of the travel plan in line with users’ preferences

### Each trip must:

* Be multi-modal. The options presented to the traveller should include three or more modes, and the demonstration of the overall system should illustrate multiple different routes, with all mode options (listed above) used at least once. Real-time data should be used, when available. Where real-time data is not available, options should be simulated;
* Be adaptive. The system should illustrate capability to adapt the route AFTER the traveller has started. This is likely to require simulation of some event that has a negative impact on the previously identified route (e.g., unexpected closure of a road, or tree on the Luas line), requiring the traveller to change modes, in real time.

### The system must:

* Be highly available. The system must be fault tolerant. The system should execute even when off-line. The system must react favourably even if data from heterogeneous sources are not available;
* Provide low latency for mobile users. This means that a decentralised architecture is likely to be most appropriate;
* Be secure. The system should be secure from attack.


# CODING STANDARDS
The coding standards for this repository can be found [here](https://github.com/trinitywayfinders/WayFinder/blob/master/Documentation/Coding_Standards/README.md)
