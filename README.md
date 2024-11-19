# WearOS Drinking Detection

## Description
My plan for my project is to create a hydration tracker (for the WearOS) that detects when you are drinking, to keep track of liquid consumed during the day

## Explanation
Main feature is that I will be using gyroscope, accelerometer, and gravity sensor to detect this automatically
After an event has been detected, it will take the information and then determine whether it was likely that you drank something. 
Of course, it will also have manual drink tracking, similar to Samsung Health or Google Health, etc.
In the case that you did, it should pop up a notification to confirm or deny (User selected) that you drank something, to increase the usability.

I plan to keep track of daily data via firebase, so as to not clog the phone/watch storage

## Motivation
Main motivation is that I've always thought it would be neat to see the statistics on how much water I drink in a day,
but im too lazy to manually increment the value every time, and even if I wasn't, I would forget about it most of the time.

## Scope and Notes from Me
(NOTE: given the difficulty of the scope of this project 
(Firebase, Sensors, Background process, notifications, determining whether it was actually a drinking motion),
I probably will not be focusing too much on UI looking nice/etc. and will probably end up skipping a few features 
(likely the background process/notifications), depending on the workload)

## Notes from Dr. Dulimarta
Proposal is approved, I support your decision to make the UI not the primary priority. 
Detecting the drinking motion (automatically) is hard but perhaps you can try to log your sensor readings over 20-40 drinking motions, 
plot the readings (using excel) and inspect any unique patterns. 
I expect to see 
- one pattern for arm swinging towards your face, 
- one pattern for "IDLE" , and
- another pattern for the opposite swinging motion to lower your arm. 
The duration of the idle time can be used as an estimate of how much you drink.
