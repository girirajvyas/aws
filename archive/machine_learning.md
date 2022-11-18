# Machine Learning

## Rekognition

### Overview
 - Find Objects, people, text, scenes, in Images and Videos using ML
 - Facial analysis and facial search to do user Verification, people counting
 - Create a Database of "Familiar Faces" r compare against celebrities
 - Use cases:
   - Labelling
   - Content Moderation
   - Text Detection
   - Face Detection and Analysis (Gender, Age range, Emotions)
   - Face Search and Verification
   - Celebrity Recognition
   - Pathing (example: for sports game analysis)

### Use case: Content moderation
 - Detect content that is inappropriate, unwanted, or offensive (images and videos)
 - Used in Social media, Broadcast media, advertising, and e-commerce situations to create a safer user experience
 - Set a minimum confidence threshold for items that will be flagged
 - Flag sensitive content for manual review in Amazon Augmented AI (A2I)
 - Help comply with regulations

## Transcribe
 - Automaticaly convert speech to text
 - uses a deep learning process called Automatic Speech Recognition (ASR) to convert speech to text quickly and accurately
 - Automatically remove Personally Identifiable Information (PII) using Redaction
 - Supports Automatic Language Identification for multi-lingual audio
 - Use cases:
   - Transcribe customer service calls
   - Automate closed captioning and subtitling
   - Generate metadata for media assets to create a fully searchable archive

## Polly
 - Opposite of Transcribe 
 - Turn text into lifelike speech using deep learning
 - Allows you to create applications that talk
 - Feature: Lexicon and SSML
   - Customize the pronouniciation of words with Pronounciation lexicons
     - Styled words: St3ph4ne -> Stephane
     - Acronyms: AWS -> Amazon Web services
   - Upload the Lixicons and use them in the Synthesize speech operation
   - Generate speech from Plain text or from documents markded up with Speech synthesis Markup Langauage (SSML) - Enables more Customization
     - Emphasizing specific words or phrases
     - Using phonetic pronounciation
     - Including breathig sounds, whispering
     - Using the Newscaster speaking style

## Translate
 - Natural and accurate Language translation
 - Amazon Translate allows you to localize content - for internatioal users, and to easily translate large volumes of text efficiently

## Amazon Lex and Connect 
 - Amazon Lex: Same technology that powers Alexa
   - Automatic speech recognition (ASR) to convert speech to text
   - Natural Language Understanding to recognize the intent of text, callers
   - Helps build chatbots, call center bots
 - Amazon Connect
   - Receive calls, create contact flows, cloud based viryual contact center
   - Can integrate with other CRM systems or AWS
   - No upfront payments, 80$ cheaper than traditional cost center solutions
   
## Amazon Comprehend
 - For Natural Language Processing (NLP)
 - Fully managed and serverless service
 - Uses Machine learning to find insights and relationships in text
   - Language of the text
   - Extracts key phrases, places, people, brands or events
   - Understands how positive or negative the text is
   - Analyzes text using tokenization and parts of speech
   - Automatically organizes a collection of text files by topic
 - Sample Use cases:
   - Analyze customer interations (Emails) to find what leads to a positive or negative experience
   - Create and Group articles by topics that Comprehend will uncover
   
## Amazon Comprehend Medical
 - Amazon Comprehend Medical detects and returns useful information in unstructured clinical text:
   - Physician's Notes
   - Discharge summaries
   - Test Results
   - Case Notes
 - Uses NLP to detect protected Health Information (PHI) - DetectPHI API
 - Store your documents in Amazon S3, Analyze realtime data with kinesis Data Firehose, or use Amazon Transcribe to transcribe patient narratives into text that can be analyzed by Amazon Comprehend Medical
 
## Amazon SageMaker
 - Fully managed service for Developers/data scientists to build ML models
 - Typically difficult to do all the process in one place  + Provision servers
 - Machine Learning process (Simplified): predicting your exam score 

## Amazon Forecast
 - Fully managed service that uses ML to deliver highly accurate forecasts
 - Example: Predict the future sales of a raincoat
 - 50% more accurate than looking at the data itself
 - Reduce forecasting time from months to hours
 - Use Cases: Product Demand planning, Financial Planning, Resource Planning

## Amazon Kendra
 - Fully Managed document search service powered by machine learning
 - Extract answers from within a document (text, pdf, HTML, Powerpoint, MS Word, FAQs)
 - Natural Language Search capabilities
 - Learn from user interactions/feedback to promote preferred results (Incremental Learning)
 - Ability to manually fine-tune search results (Importance of data, freshness, Custom, etc)

## Amazon Personalize
 - Fully managed ML-Service to build apps with real-time personalized recommendations
 - Example: Personalized product recommendations-ranking, customized direct marketing
   - Example: User brought gardening tools, provide recommendations on the next one to buy
 - Same technology is used by Amazon.com

## Amazon Textract 
 - Automatically extracts text, handwriting and data from any scanneddocuments using AI and ML
 - Extract data from forms and tables
 - Read and process any type of document (PDFs, Images )
 - Use cases: 
   - Finanial Services (eg: Invoices, financial reports)
   - HealthCare (eg: medical records, insurance claims)
   - Public Sector (eg: tax forms, ID docuemnts, passports)

## Machine Learning Summary
 - **Rekognition: ** Face detection, Labelling, Celebrity recognition
 - **Transcribe: ** audio to text (ex: subtitles)
 - **Polly: ** text to audio
 - **Translate: ** translations
 - **Lex: ** build conversational bots - chatbots
 - **Connect: ** cloud contact center
 - **Comprehend: ** Natural Language Processing
 - **SageMaker: ** Machine Learning for every developer and data scientist
 - **Forecast: ** Build highly accurate forecasts
 - **Kendra: ** ML-powered search engines
 - **Personalize: ** Real time personalized recommendations
 - **Textract: ** Detect text and data in documents




     


 