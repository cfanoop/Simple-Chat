# Simple-Chat
Simple Chat in Java

Command Line Chat Application.

  Configure the recievers ip address and port in properties file. 
  Message directly to recievers screen using UDP sockets
  No servers in between, No history, No User Interfaces
  
  
How to use

  1)  Enter the senders port and recievers address/port in mcp.properties (Multiple revievers can be configured)
    
      eg :
         idname=Anoop
         send.port=10000
         recieve.port=11000
         
         reciever.Appu.ip=168.194.0.92
         reciever.Appu.port=12000
         
         reciever.Miku.ip=168.194.0.96
         reciever.Miku.port=14000
        
  2)  Run the application
  
  3)  In the screen, type "Appu:hi" to send the "hi" message to Appu. (Application should be running in Appu's machine to recieve the messages). Once the message send to Appu he becomes the current reciever and no need to mention the name again in the successive messages. Just type the message and it will route to Appu. To chat to new user again mention the name and the message "Miku:Hello". Then Miku will be current reciever.
      The messaes from the users will be shown in the screen. Once the message from a friend recieved, he becomes current reciever and the messages entered without name will be send to the same person.
            
      
Author
  Anoop C Francis
  cfanoop@gmail.com
  
  
Please send you feedback to cfanoop@gmail.com


  
 
  
  
  
  

