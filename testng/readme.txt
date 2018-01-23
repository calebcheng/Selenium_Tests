param 'suiteEnv' = [String] The environment properties folder. i.e. if your automation prop files are under config/EnvironmentProperties/test1 you must input 'test1' for this value. 

param 'suiteDesc' = [String] Include the platform version here if you did not include it in PlatformConfiguration.xml i.e. your suite description could be '10.64: Regression testing 2.92 MEG with xxx'. This string will be displayed inside the generated report. 

param 'uploadReport' = [true/false] if 'true' your automation report will be uploaded to central report repo after it finishes. See 'confirmBeforeUpload' for additional prompt. 

param 'confirmBeforeUpload' = [true/false] if  'uploadReport' is true - true here will prompt you at the end of the automation run to confirm you want to upload, false here will not prompt and just upload straight away. 

param 'emailReport' = TODO - do not use (19/09/17)

if <test> in <suite> tag contains parallel="classes", then each testcase will be run in parallel. e.g. <test name="Sanity" parallel="classes">. Talk to dev before enabling this on anything new, as classes need to be thread safe first. 