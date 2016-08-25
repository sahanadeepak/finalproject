
<%@page import="DataBase.DBQuery"%>
<%@page import="java.util.Random"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="java.io.File"%>
<%
ServletContext context = getServletContext();
String dirName =context.getRealPath("\\");
System.out.println("????????????????????????????????"+dirName);
File file2 = null;
String paramname=null,fname="",file="",filePath="",user="";
//user=request.getParameter("user");
//System.out.println(".."+user);
try {
			 
			MultipartRequest multi = new MultipartRequest(request, dirName,	10 * 1024 * 1024); // 10MB
                       
			Enumeration params = multi.getParameterNames();
			while (params.hasMoreElements()) 
			{
				paramname = (String) params.nextElement();
				if(paramname.equalsIgnoreCase("user"))
				{
					user=multi.getParameter(paramname);
                                        System.out.println("user:::"+user);
				}
				
                                
                        
                         }
        Enumeration files = multi.getFileNames();	
	while (files.hasMoreElements()) 
	{
		paramname = (String) files.nextElement();
	/*	if(paramname.equals("d1"))
		{
			paramname = null;
		}*/
		String fPath="";
		 if(paramname != null && paramname.equals("uploaded_file"))
		{
			
			filePath = multi.getFilesystemName(paramname);
			fPath = dirName+filePath;
                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>1>>"+filePath);

                        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>2>>"+fPath);

		
                        file2 = new File(fPath);
                        try{
//                    Random r=new Random();
//                    int ii=r.nextInt(10000);
//                    String s[]=filePath.split("_");
//                    System.out.println("s[1]>>>"+s[1]);
//                    String user1=s[1];
//                    int index=user1.indexOf(".");
//                    System.out.println("................"+index);
//                    user1=user1.replace(".", "_");
//                    String ss[]=user1.split("_");
                    System.out.println("ss[0]....>>"+filePath);
                       // file2.renameTo(new File("C:/Users/Vinay/Desktop/Project/FlashMessenger/web//userPhoto//"+ss[0]+".jpg"  ));//file2.getName()
                        file2.renameTo(new File("/Users/RJ/Desktop/SesameServer"+filePath  ));//file2.getName()
              DBQuery db=new DBQuery();
            //  int i=db.add_user("F:/ALL 2016 PROJECTS/SesameServer/"+filePath);
                        }catch(Exception e){e.printStackTrace();}
		}
          }
          }catch(Exception e)
                                   {
          e.printStackTrace();
          }



%>