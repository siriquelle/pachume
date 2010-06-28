<%@page import="java.io.File" %>
<%@page import="javax.xml.parsers.*" %>
<%@page import="org.w3c.dom.*" %>
<%@page import="java.net.*" %>
<%@page import="java.io.InputStream" %>
<%@page import="org.xml.sax.*" %>
<%@page import="command.conversion.MakeCompatable" %>
<%@page import="java.io.BufferedReader" %>
<%@page import="java.io.InputStreamReader" %>

<%
        try {
            DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();

            URL url = new URL("http://search.twitter.com/search.atom?q=%23picturegate");
            InputStream stream = url.openStream();
            Document doc = docBuilder.parse(stream);

            // normalize text representation
            doc.getDocumentElement().normalize();
            System.out.println("Root element of the doc is " + doc.getDocumentElement().getNodeName());

            NodeList parentElement = doc.getElementsByTagName("entry");
            this.scrape(parentElement);

        } catch (SAXParseException err) {
            System.out.println("** Parsing error" + ", line " + err.getLineNumber() + ", uri " + err.getSystemId());
            System.out.println(" " + err.getMessage());
        } catch (SAXException e) {
            Exception x = e.getException();
            ((x == null) ? e : x).printStackTrace();
        } catch (Throwable t) {
            t.printStackTrace();
        }
%>

<%!
    public String scrape(NodeList parentElement) {
        String feedback = "Error";
        int totalPersons = parentElement.getLength();
        System.out.println("Total no of people : " + totalPersons);
            Node firstPersonNode = parentElement.item(0);
            if (firstPersonNode.getNodeType() == Node.ELEMENT_NODE) {
                Element firstPersonElement = (Element) firstPersonNode;
                //-------
                NodeList firstNameList = firstPersonElement.getElementsByTagName("title");
                Element firstNameElement = (Element) firstNameList.item(0);
                NodeList textFNList = firstNameElement.getChildNodes();

                String title = ((Node) textFNList.item(0)).getNodeValue().trim().toString();
                title = this.removeCommonAttribution(title);
                System.out.println("Element Content : " + title);

                NodeList linkList = firstPersonElement.getElementsByTagName("link");
                Element linkElement = (Element) linkList.item(0);
                String href = linkElement.getAttribute("href");

                System.out.println("Element link : " + href);

                title += " > " + href;
                System.out.println("title: " + title);

                feedback = this.sendToPachume(title);

            System.out.println("pachume says : " + feedback);

        }//end of if clause
        return feedback;
    }
%>

<%!    public String sendToPachume(String title) {
        String feedback = "Error";

        try {
            title = URLEncoder.encode(title, "UTF-8");
            System.out.println("title: " + title);

            URL url2 = new URL("http://pachume.com/API?username=picturegate&password=georgegeorg3&pachume=" + title);
            URLConnection con = url2.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            feedback = in.readLine();
            in.close();

            System.out.println("pachume says : " + feedback);

        } catch (Exception e) {
            feedback = e.toString();
        }

        return feedback;
    }
%>

<%!  public String removeCommonAttribution(String text) {

        text = text.replaceFirst("You: ", "");

        return text;
    }%>