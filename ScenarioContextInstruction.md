=================== How to share data Between Steps. ============================
eg: you want to store PRODUCT_NAME in a step of Login_Steps. 
And want to get that PRODUCT_NAME value in another step of HomePageSteps.
- in Login_Steps, declare world object @Autowired private World world;
- save the value into context variable using below code:

# case1: Store a String Variable
world.scenarioContext = new ScenarioContext();
String product_name = "Testing Sharing data"
world.scenarioContext.setContext(Context.PRODUCT_NAME,product_name);
# case2: Store a List
world.scenarioContext = new ScenarioContext();
List<String> list_product = new ArrayList<>();
list_product.add("Test1");
world.scenarioContext.setContext(Context.PRODUCT_NAME,list_product);
# case3: Store a Map
world.scenarioContext = new ScenarioContext();
Map<String, String> productMap = new HashMap<String, String>();
productMap.put("name","Test1");
world.scenarioContext.setContext(Context.PRODUCT_NAME,productMap);

- Get the PRODUCT_NAME from scenarioContext in HomePageSteps.
/* Cast to String if the saved Context is String Object*/
String productName = (String)world.scenarioContext.getContext(Context.PRODUCT_NAME);
/* Cast to ArrayList if the saved Context is ArrayList Object*/
ArrayList productName = (ArrayList)world.scenarioContext.getContext(Context.PRODUCT_NAME);
/* Cast to HashMap if the saved Context is HashMap Object*/
HashMap productName = (HashMap)world.scenarioContext.getContext(Context.PRODUCT_NAME);




