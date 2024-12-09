# **RainCheck Requirement Specification**
## **1. Current Situation**
Our business, RainCheck, provides simple and quick forecasts for weather developments. We offer accurate and reliable information to users, who can get a weather forecast by a phone call/sms.

## **2. Dream System**
We would like a website where our users can check forecasts for all settlements across the world, including 1-day, 1-week, and 2-week forecasts, with the 1-day forecast being the most detailed and the 2-week forecast the least detailed. Additionally, we want the site to function responsively and to be as easy to use as possible.

## **3. Current Business Processes**
Weather reporting tied to location. Call or send a message to the dispatcher and they will let you know about the weather to that specific location.

## **4. Required Business Processes**
Our application will use the free version of an API called **Visual Crossing Weather** which limits how many calls a day our users can make.  
On demand we can switch to a version with a paywall, see [Pricing](https://www.visualcrossing.com/weather-data-editions) here.
### **4.1. Online Presence**
#### **4.1.1. Reading Forecasts**
Selecting a settlement => setting the interval => reading the forecast.

## **5. Rules for the System**
The web interface should be developed using standard tools, such as HTML/CSS/JavaScript or Angular framework tools. Images and videos can be in JPEG, SVG, MP4, GIF and PNG formats. Legal requirements must be observed for websites identifying users: GDPR, ...

## **6. Requirement List**
| **ID** | **Name**                                       | **Category**        | **Requirement Description**                                                                 |
|--------|---------------------------------------------|---------------------|-------------------------------------------------------------------------------------------|
| R1     | Real-Time Weather Forecast                 | Functional          | The application must provide real-time weather forecasts.                                 |
| R2     | City-Based Filtering                       | Functional          | Users must be able to filter by cities from anywhere in the world.                        |
| R3     | Forecast Time Range Selection              | Functional          | Users must be able to choose a one-day, one-week, or two-week forecast.                   |
| R4     | One-Day Forecast Details                   | Functional          | The one-day forecast must display hourly temperature, precipitation amount, and type.     |
| R5     | Weekly and Bi-Weekly Forecast Details      | Functional          | Weekly and bi-weekly forecasts must show daily maximum and minimum temperatures, as well as precipitation details. |
| R6     | Theme Selector                             | UI/UX               | Users must be able to select themes (rainy, sunny, snowy) that reflect the weather.       |
| R7     | Persistent Theme Selector                  | UI/UX               | The theme selector must remain visible as users scroll through the website.               |
| R8     | Homepage Video                             | UI/UX               | A looping video showing sunny but cloudy weather must be displayed on the homepage.       |
| R9     | Background Colors for Themes              | UI/UX               | Background colors must reflect themes: turquoise for rainy, warm colors for sunny, and light blue for snowy. |
| R10    | Default Weather Location                   | Functional          | The site must display Budapest's weather on initial load for Hungarian users.             |
| R11    | Temperature Scale                          | Functional          | Temperatures must be displayed in Celsius.                                                |
| R12    | Precipitation Unit                         | Functional          | Precipitation amounts must be displayed in centimeters.                                   |
| R13    | City and Country Details                   | Functional          | The city name, country, state, and the forecast date must be displayed.                   |
| R14    | Scroll-to-Forecast Button                  | Functional          | A button must navigate users directly to the forecast section.                            |
| R15    | Temporary Weather Data Storage            | Functional          | Store weather data for searched cities daily and clear at midnight to ensure freshness.   |
| R16    | Daily Query Limit                          | Non-Functional      | The application must support up to 1,000 queries per day with the current database plan.  |
| R17    | Language and Target Audience               | Non-Functional      | The site must be in English, focusing on the Hungarian market initially.                  |
| R18    | Glass-Like Panel Design                    | UI/UX               | Glass-like panels must be incorporated into the application design.                       |



## **7. Glossary**
GDPR: The GDPR (General Data Protection Regulation) is the European Union's General Data Protection Regulation.
