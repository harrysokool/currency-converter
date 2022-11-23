# CPSC210 Personal Project

## A Currency Converter

- This application will allow users to transfer one currency to another
currency with a live currency rate. There will be multiple currencies
for the user to select. Many people will use currency converters
because these days, people travel around the world very frequently,
so it is essential to check the local currency rate. Online shopping 
has become increasingly popular, and people buy items at different
websites from different countries. Therefore, it is important to check
the price of another currency. 

- This project is very interesting because it helps people to know
whether it is the right time to exchange their money for another
currency or not because sometimes a country's currency may drop and 
Currency converter can check whether it is the right time or not, it
helps to determine it. It also helps to determine a country's economic
health.

## User Stories
- As a user, I want to be able to add a currency to a currency list.
- As a user, I want to be able to view the list of currencies in the currency list.
- As a user, I want to be able to remove a currency from currency list.
- As a user, I want to be able to enter the amount of a currency and 
  transfer it into another currency from the currency list.
- As a user, I want to be able to save my currency list to file
- As a user, I want to be able to be able to load my currency list from file 

# Instructions for Grader

- You can generate the first required event related to adding currencies to a currency list by typing in the name
of currency and the rate of currency and click the add button
- You can generate the second required event related to removing a currency from the currency list by type in the name 
of the currency then click the remove button.
- You can convert the currency by typing in the first currency and the second currency and type in the amount and press
convert.
- You can locate my visual component by opening the application.
- You can save the state of my application by clicking the save button.
- You can reload the state of my application by clicking the load button.


# Phase 4: Task 2
Mon Nov 21 14:45:11 PST 2022
Added currency: HKD


Mon Nov 21 14:45:14 PST 2022
Added currency: USD


Mon Nov 21 14:45:21 PST 2022
Added currency: AUD


Mon Nov 21 14:45:29 PST 2022
CAD converted to HKD


Mon Nov 21 14:45:37 PST 2022
CAD converted to USD


Mon Nov 21 14:45:40 PST 2022
Removed currency: AUD


Mon Nov 21 14:45:54 PST 2022
USD converted to HKD

# Phase 4: Task 3
If I had more time to work on the project, i would refactor some 
duplicate codes, since modifying the behaviour of one involves 
modifying the behaviour of the other method. Similar to this, 
if I discover a bug in one place, I must also correct it 
in the other place. It is easy to forget to debug. So I think 
it would be a great idea to refactor duplicate codes. There are 
duplicate codes, such as the code in the panel class. To fix this,
I can extract a method, and call that method from inside each of 
the original methods instead.

