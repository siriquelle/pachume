/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.LoginBean;
import bean.channelBean;
import bean.groupBean;
import bean.userBean;
import command.channel.DestroyChannel;
import command.channel.FollowChannel;
import command.channel.GetChannelMembers;
import command.channel.GetChannelName;
import command.channel.IsChannelNameAvailable;
import command.channel.IsUserChannelsAuthor;
import command.channel.MakeChannel;
import command.channel.StopFollowingChannel;
import command.channel.UpdateBackgroundColor;
import command.channel.UpdateBackgroundImageLocation;
import command.channel.UpdateBackgroundImageRepeat;
import command.channel.UpdateChannelAvatarLocation;
import command.comment.IsUsersComment;
import command.comment.PostComment;
import command.comment.RemoveComment;
import command.comment.SpamThisComment;
import command.conversion.ConvertImageToJPEG;
import command.conversion.ConvertToSHA1;
import command.conversion.GetGoTo;
import command.conversion.MakeCompatable;
import command.conversion.ScaleImage;
import command.group.DestroyGroup;
import command.group.DoesGroupExist;
import command.group.FollowGroup;
import command.group.GetGroupId;
import command.group.GetGroupName;
import command.group.IsGroupNameAvailable;
import command.group.IsUserGroupsAuthor;
import command.group.MakeGroup;
import command.group.ResetGroupId;
import command.group.UpdateGroupAvatarLocation;
import command.group.UpdateGroupBackgroundColor;
import command.group.UpdateGroupBackgroundImageRepeat;
import command.notification.SendEmail;
import command.pachume.DestroyPachume;
import command.pachume.InsertTags;
import command.pachume.MakePachume;
import command.pachume.SpamThisPachume;
import command.user.FollowUser;
import command.user.GetEmailFromUserId;
import command.user.GetUserId;
import command.user.GetUsersContacts;
import command.user.IsUsersPost;
import command.user.MakeUser;
import command.user.StopFollowingUser;
import command.user.settings.AlterAvatarLocation;
import command.user.settings.AlterBackgroundImageLocation;
import command.user.settings.AlterBackgroundImageRepeat;
import command.user.settings.SetUsersApiKey;
import command.verification.IsEmailAvailable;
import command.verification.IsScreenNameAvailable;
import command.verification.RemoveAnyCookies;
import command.verification.VerifyEmail;
import command.verification.VerifyFirstName;
import command.verification.VerifyLastName;
import command.verification.VerifyLogin;
import command.verification.VerifyPassword;
import java.awt.Image;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;
import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import model.User;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.safehaus.uuid.UUIDGenerator;
import template.Email;
import template.EmailUpdatedNotificationEmail;
import template.Email_ErrorNotification;
import template.Email_NotifyOfFollower;
import template.JoinGroupRequestEmail;

/**
 *
 * @author Siriquelle
 */
public class Main extends Base {
//    
//
    // <editor-fold defaultstate="collapsed" desc="home">

    private void home(HttpServletRequest request, HttpServletResponse response) {
        String GoTo = "/";
        try
          {
            this.redirect(request, response, GoTo);
          } catch (Exception e)
          {
            this.error(request, response, e);
          }
    }
// </editor-fold>
//
//
    // <editor-fold defaultstate="collapsed" desc="doGet">

    /**
     * Handles the HTTP <code>Get</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String path = request.getServletPath();

        try
          {
            if (path == null || path.length() == 0)
              {
                this.home(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/"))
              {
                this.home(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/DeleteChannel"))
              {
                this.deleteChannel(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/DeleteComment"))
              {
                this.deleteComment(request, response);
              } //
            //            
            else if (path.equalsIgnoreCase("/DeleteGroup"))
              {
                this.deleteGroup(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/DeletePachume"))
              {
                this.deletePachume(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/JoinChannel"))
              {
                this.joinChannel(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/JoinGroup"))
              {
                this.joinGroup(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/LeaveChannel"))
              {
                this.leaveChannel(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/LeaveGroup"))
              {
                this.leaveGroup(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/Logout"))
              {
                this.logout(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/SpamComments"))
              {
                this.spamComment(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/SpamPachume"))
              {
                this.spamPachume(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/addFriend"))
              {
                this.addFriend(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/removeFriend"))
              {
                this.removeFriend(request, response);
              }//

          } catch (Exception e)
          {
            this.error(request, response, e);
          }

    }
// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="deleteChannel">

    private void deleteChannel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = new User();
            thisUser = (User) session.getAttribute("thisUser");

            if (goTo.contains("channel.jsp"))
              {
                goTo = "/channels.jsp?";
              }

            int userId = thisUser.getUserId();
            int channelId = -1;

            if (request.getParameter("channelId") != null)
              {
                channelId = Integer.parseInt(request.getParameter("channelId"));

                if (thisUser.isLoggedIn())
                  {
                    if (IsUserChannelsAuthor.run(channelId, session))
                      {
                        if (GetChannelMembers.run(channelId).size() < 2)
                          {
                            if (DestroyChannel.run(userId, channelId))
                              {
                                goTo += "message=that channel has been deleted";
                              } else
                              {
                                goTo += "message=that channel could not be deleted at this time";
                              }

                          } else
                          {
                            goTo += "message=you can only delete a channel if you are the only member remaining";
                          }
                      } else
                      {
                        goTo += "message=you do not have the authority to delete that channel";
                      }
                  } else
                  {
                    goTo += "message=please log in to perform this action";
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }
        this.redirect(request, response, goTo);
    }// </editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="deleteComment">

    private void deleteComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = new User();
            thisUser = (User) session.getAttribute("thisUser");

            int userId = thisUser.getUserId();
            int commentId = -1;

            if (request.getParameter("commentId") != null)
              {
                commentId = Integer.parseInt(request.getParameter("commentId"));

                if (thisUser.isLoggedIn())
                  {
                    if (IsUsersComment.run(commentId, session))
                      {
                        if (RemoveComment.run(userId, commentId))
                          {
                            goTo += "message=comment has been deleted";
                          }
                      }
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);

    }// </editor-fold>
//
    // <editor-fold defaultstate="collapsed" desc="deleteGroup">

    private void deleteGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = new User();
            thisUser = (User) session.getAttribute("thisUser");

            if (goTo.contains("group.jsp"))
              {
                goTo = "/groups.jsp?";
              }

            int userId = thisUser.getUserId();

            if (request.getParameter("groupName") != null)
              {
                String groupName = request.getParameter("groupName");
                String groupId = GetGroupId.run(groupName);

                if (thisUser.isLoggedIn())
                  {
                    if (IsUserGroupsAuthor.run(groupId, session))
                      {
                        if (DestroyGroup.run(userId, groupId))
                          {
                            goTo += "message=that group has been deleted";
                          } else
                          {
                            goTo += "message=that group could not be deleted at this time";
                          }
                      } else
                      {
                        goTo += "message=you do not have the authority to delete that group";
                      }
                  } else
                  {
                    goTo += "message=please log in to perform this action";
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }
        this.redirect(request, response, goTo);
    }// </editor-fold>    
//    
    // <editor-fold defaultstate="collapsed" desc="deletePachume">

    private void deletePachume(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);

        try
          {
            HttpSession session = request.getSession();
            User thisUser = new User();

            thisUser = (User) session.getAttribute("thisUser");

            if (goTo.contains("comment.jsp"))
              {
                goTo = "/?";
              }

            int userId = thisUser.getUserId();
            int pachumeId = -1;

            if (request.getParameter("pachumeId") != null)
              {
                pachumeId = Integer.parseInt(request.getParameter("pachumeId"));

                if (thisUser.isLoggedIn())
                  {
                    if (IsUsersPost.run(pachumeId, session))
                      {
                        if (DestroyPachume.run(userId, pachumeId))
                          {
                            goTo += "message=pachume has been deleted";
                          }
                      }
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);
    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="joinChannel">

    private void joinChannel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);

        try
          {
            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");
            int channelId = Integer.parseInt(request.getParameter("channelId"));

            if (channelId > 0)
              {
                FollowChannel.run(thisUser.getUserId(), channelId);
                goTo += "message=you have joined this channel";
              }
          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);

    }// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="joinGroup">
    private void joinGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");
            String groupId = request.getParameter("groupId").trim();

            if (groupId.length() > 0)
              {
                if (DoesGroupExist.run(groupId))
                  {
                    FollowGroup.run(thisUser.getUserId(), groupId);
                    String groupName = GetGroupName.run(groupId);
                    ResetGroupId.run(groupId);
                    goTo += "message=you have become a member of the private group " + groupName;
                  } else
                  {
                    goTo += "message=the group matching that id does not or no longer exists";
                  }
              }
          } catch (Exception e)
          {
            goTo += "message=please login into pachume to join this group +++ " + e.toString();
          }

        this.redirect(request, response, goTo);

    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="leaveChannel">

    private void leaveChannel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);

        try
          {

            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");

            int channelId = Integer.parseInt(request.getParameter("channelId"));

            if (thisUser.getUserId() > 0)
              {
                StopFollowingChannel.run(thisUser.getUserId(), channelId);
                goTo += "message=you are no longer following this channel";
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);

    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="leaveGroup">

    private void leaveGroup(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");

            String groupId = request.getParameter("groupId").trim();

            if (thisUser.getUserId() > 0)
              {
                command.group.StopFollowingGroup.run(thisUser.getUserId(), groupId);
                goTo = "/?message=you are no longer a member of " + GetGroupName.run(groupId);
              }
          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);


    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="logout">

    private void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = "/login.jsp?";
        try
          {
            RemoveAnyCookies.run(request, response);
            HttpSession session = request.getSession();
            session.invalidate();
            goTo += "message=you are logged out on this computer";
          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }
        this.redirect(request, response, goTo);

    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="spamComment">

    private void spamComment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = new User();

            thisUser = (User) session.getAttribute("thisUser");

            int userId = thisUser.getUserId();
            String commentId = null;

            if (request.getParameter("commentId") != null)
              {
                commentId = request.getParameter("commentId");

                if (SpamThisComment.run(userId, commentId))
                  {
                    goTo += "message=pachume has been marked as spam";
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);

    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="spamPachume">

    private void spamPachume(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = new User();

            thisUser = (User) session.getAttribute("thisUser");

            int userId = thisUser.getUserId();
            String pachumeId = null;

            if (request.getParameter("pachumeId") != null)
              {
                pachumeId = request.getParameter("pachumeId");

                if (SpamThisPachume.run(userId, pachumeId))
                  {
                    goTo += "message=pachume has been marked as spam";
                  }
              }
          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }
        this.redirect(request, response, goTo);
    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="addFriend">

    private void addFriend(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);

        try
          {
            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");
            int userId = thisUser.getUserId();
            int friendId = Integer.parseInt(request.getParameter("id"));


            if (friendId > 0)
              {
                FollowUser.run(userId, friendId);
                String userEmail = GetEmailFromUserId.run(userId);
                String friendEmail = GetEmailFromUserId.run(friendId);
                Email Email = new Email_NotifyOfFollower(userEmail, friendEmail);

                if (SendEmail.run(friendEmail, Email.getSubject(), Email.getBody()))
                  {
                    System.out.print("message=emial sent : success");
                  } else
                  {
                    System.out.print("message=emial sent : failure");
                  }
                goTo += "message=contact added";
              }
          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);

    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="removeFriend">

    private void removeFriend(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");
            int friendId = Integer.parseInt(request.getParameter("id"));

            if (thisUser.getUserId() > 0)
              {
                StopFollowingUser.run(thisUser.getUserId(), friendId);
              }
          } catch (Exception e)
          {
            goTo += "message=" + e;
          }

        this.redirect(request, response, goTo);

    }// </editor-fold>
//    
//    
    // <editor-fold defaultstate="collapsed" desc="doPost">

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        String path = request.getServletPath();

        try
          {
            if (path == null || path.length() == 0)
              {
                this.home(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/"))
              {
                this.home(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/PostComment"))
              {
                this.PostComment(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/CreateChannel"))
              {
                this.createChannel(request, response);
              } //
            //            
            else if (path.equalsIgnoreCase("/CreateGroup"))
              {
                this.createGroup(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/Join"))
              {
                this.join(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/Login"))
              {
                this.login(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/Pachume"))
              {
                this.pachume(request, response);
              } //            
            //            
            else if (path.equalsIgnoreCase("/UpdateEmail"))
              {
                this.updateEmail(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/UpdateLocation"))
              {
                this.updateLocation(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/changeBackgroundColor"))
              {
                this.changeBackgroundColor(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/changeBackgroundImageRepeat"))
              {
                this.changeBackgroundImageRepeat(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/changeChannelBackgroundColor"))
              {
                this.changeChannelBackgroundColor(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/changeChannelBackgroundImageRepeat"))
              {
                this.changeChannelBackgroundImageRepeat(request, response);
              } //            
            //
            else if (path.equalsIgnoreCase("/changeGroupBackgroundColor"))
              {
                this.changeGroupBackgroundColor(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/changeGroupBackgroundImageRepeat"))
              {
                this.changeGroupBackgroundImageRepeat(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/changeNotification"))
              {
                this.changeNotification(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/inviteToGroup"))
              {
                this.inviteToGroup(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/uploadBackgroundImage"))
              {
                this.uploadBackgroundImage(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/uploadChannelAvatar"))
              {
                this.uploadChannelAvatar(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/uploadChannelBackgroundImage"))
              {
                this.uploadChannelBackgroundImage(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/uploadGroupAvatar"))
              {
                this.uploadGroupAvatar(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/uploadGroupBackgroundImage"))
              {
                this.uploadGroupBackgroundImage(request, response);
              }//
            //            
            else if (path.equalsIgnoreCase("/uploadImage"))
              {
                this.uploadImage(request, response);
              }//
//            
          } catch (Exception e)
          {
            this.error(request, response, e);
          }


    }
    // </editor-fold>
//      
    // <editor-fold defaultstate="collapsed" desc="PostComment">

    private void PostComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        int commentId = 0;
        try
          {
            User loggedUser = new User();
            HttpSession session = request.getSession();
            loggedUser = (User) session.getAttribute("thisUser");

            String comment = "comment";
            int pachumeId = 0;

            comment = request.getParameter(comment);
            int userId = loggedUser.getUserId();
            pachumeId = Integer.parseInt(request.getParameter("pachumeId"));

            commentId = PostComment.run(comment, userId, pachumeId);

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
            Email email = new Email_ErrorNotification(e.toString(), Integer.parseInt(request.getParameter("pachumeId")));
            SendEmail.run("pachume@pachume.com", email.getSubject(), email.getBody());
          }

        this.redirect(request, response, goTo);
    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="createChannel">

    private void createChannel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);

        try
          {

            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");

            int userId = thisUser.getUserId();

            String avatarLocation = "/media/images/channel.png";
            String contactImageLocation = "/media/images/channelContact.png";
            String backgroundImageLocation = "/media/images/bg.png";
            String backgroundImageRepeat = "repeat";
            String backgroundColor = "#ffd";

            String channelName = request.getParameter("channelName");
            channelName = MakeCompatable.run(channelName);

            String channelDescription = request.getParameter("channelDescription");
            channelDescription = MakeCompatable.run(channelDescription);


            int groupGate = 0;

            if (IsChannelNameAvailable.run(channelName) && !channelName.equals(""))
              {
                groupGate++;
              } else
              {
                goTo += "message=please use a different name";
              }

            if (!channelDescription.equals(""))
              {
                groupGate++;
              } else
              {
                goTo += "message=you should give this group a description.";
              }

            if (groupGate == 2)
              {
                int channelId = MakeChannel.run(userId, channelName, channelDescription, avatarLocation, contactImageLocation, backgroundImageLocation, backgroundImageRepeat, backgroundColor);

                if (channelId > -1)
                  {
                    boolean isChannelJoined = FollowChannel.run(userId, channelId);

                    if (isChannelJoined)
                      {
                        goTo += "message=channel is up and running.";
                      }
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);
    }// </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="createGroup">

    private void createGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);

        try
          {

            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");

            int userId = thisUser.getUserId();
            String groupName = "";
            String groupDescription = "";


            String avatarLocation = "/media/images/group.png";
            String contactImageLocation = "/media/images/groupContact.png";
            String backgroundImageLocation = "/media/images/bg.png";
            String backgroundImageRepeat = "repeat";
            String backgroundColor = "#ffd";

            UUIDGenerator joe = UUIDGenerator.getInstance();
            String uuid = joe.generateRandomBasedUUID().toString();
            String groupId = uuid;

            groupName = request.getParameter("groupName");
            groupName = MakeCompatable.run(groupName);

            groupDescription = request.getParameter("groupDescription");
            groupDescription = MakeCompatable.run(groupDescription);

            int groupGate = 0;

            if (IsGroupNameAvailable.run(groupName) && !groupName.equals(""))
              {
                groupGate++;
              } else
              {
                goTo += "message=please use a different name";
              }

            if (!groupDescription.equals(""))
              {
                groupGate++;
              } else
              {
                goTo += "message=you should give this group a description";
              }

            if (groupGate == 2)
              {
                groupId = MakeGroup.run(groupId, userId, groupName, groupDescription, avatarLocation, contactImageLocation, backgroundImageLocation, backgroundImageRepeat, backgroundColor);
                if (groupId.length() > 0)
                  {
                    boolean isChanelJoined = FollowGroup.run(userId, groupId);

                    if (isChanelJoined)
                      {
                        goTo += "message=group is up and running";
                      }
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="join">

    private void join(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        String goTo = "/join.jsp?";

        try
          {
            User thisUser = new User();
            HttpSession session = thisUser.saveUser(request);
            thisUser = (User) session.getAttribute("thisUser");
            LoginBean loginBean = new LoginBean();

            String screenName = thisUser.getScreenName();
            String email = thisUser.getEmail();

            String firstName = thisUser.getFirstName();
            String lastName = thisUser.getLastName();

            String userPassword = thisUser.getUserPassword();
            String verifyPassword = thisUser.getVerifyPassword();

            String lock = "closed";
            if ((String) session.getAttribute("lock") != null)
              {
                lock = (String) session.getAttribute("lock");
              }

            int joinGate = 0;

            if (IsScreenNameAvailable.run(screenName))
              {
                if (screenName.length() > 15)
                  {
                    goTo += "message=keep screen name under 15 characters in length;";
                  } else if (screenName.contains(" "))
                  {
                    goTo += "message=keep screen should not have spaces;";
                  } else
                  {
                    joinGate++;
                    loginBean.setScreenName(screenName);
                  }
              } else
              {
                goTo += "message=the screen name you selected is no longer available;";
              }

            if (IsEmailAvailable.run(email))
              {
                if (VerifyEmail.run(email))
                  {
                    joinGate++;
                    loginBean.setEmail(email);
                  } else
                  {
                    goTo += "message=the Email you entered is not valid;";
                  }
              } else
              {
                goTo += "message=the Email you entered is already in use;";
              }


            if (VerifyPassword.run(userPassword, verifyPassword))
              {
                joinGate++;
              } else
              {
                goTo += "message=The passwords you entered do not match;";
              }

            if (VerifyFirstName.run(firstName))
              {
                joinGate++;
                loginBean.setFirstName(firstName);
              } else
              {
                goTo += "message=Please enter a first name;";
              }

            if (VerifyLastName.run(lastName))
              {
                joinGate++;
                loginBean.setLastName(lastName);
              } else
              {
                goTo += "message=Please enter a last name;";
              }

            if (lock.equals("open"))
              {
                joinGate++;
              } else
              {
                goTo += "message=Please enter the correct CAPTCHA code;";
              }

            if (joinGate == 6)
              {
                try
                  {
                    String role = thisUser.getRole();
                    String backgroundColor = thisUser.getBackgroundColor();
                    String backgroundImageLocation = thisUser.getBackgroundImageLocation();
                    String avatarLocation = thisUser.getAvatarLocation();
                    String pachumeImageLocation = thisUser.getAvatarLocation();
                    String contactImageLocation = thisUser.getAvatarLocation();

                    String location = thisUser.getLocation();
                    String feedLocation = thisUser.getFeedLocation();
                    String college = thisUser.getCollege();
                    boolean active = true;
                    boolean notifiable = true;
                    int channelId = -1;
                    String channelName = "home";
                    String groupId = "00000000-0000-0000-0000-000000000000";

                    boolean createUser = MakeUser.run(firstName, lastName, screenName, email, userPassword, role, backgroundColor, backgroundImageLocation, avatarLocation, feedLocation, location, college, active, pachumeImageLocation, contactImageLocation, notifiable);

                    if (createUser == true)
                      {
                        int userId = GetUserId.run(screenName);
                        boolean apiKey = SetUsersApiKey.run(userId);

                        thisUser.setUserId(userId);
                        MakePachume.run(userId, "joined pachume!", "pachume", "public", false, "", channelId, channelName, groupId);
                        FollowUser.run(userId, 1);
                        Vector contactList = GetUsersContacts.run(userId);
                        thisUser.setContactList(contactList);
                        //...///.../////////...///...//
                        ///////////////////////////////
                        //...///.../////////...///...//
                        ///////////////////////////////
                        thisUser.setActive(true);
                        thisUser.setLoggedIn(true);
                      ///////////////////////////////
                      //...///.../////////...///...//
                      ///////////////////////////////
                      //...///.../////////...///...//

                      }

                    goTo = "/?message=Welcome to pachume";

                  } catch (Exception e)
                  {
                    goTo += "message=" + e.toString();
                  }

                session.setAttribute("thisUser", thisUser);

              } else
              {
                session.removeAttribute("thisUser");
                session.setAttribute("loginBean", loginBean);
                goTo += "message=Error: " + joinGate + ";";
              }

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }
        this.redirect(request, response, goTo);
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="login">

    private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String goTo = "/login.jsp?";
        try
          {
            //Get Username
            String screenName = null;
            if (request.getParameter("screenName") != null)
              {
                screenName = request.getParameter("screenName");
              }

            //Get Password
            String userPassword = null;
            if (request.getParameter("userPassword") != null)
              {
                userPassword = request.getParameter("userPassword");
                userPassword = ConvertToSHA1.run(userPassword);
              }

            //Get saveSession
            String saveSession = "false";
            if (request.getParameter("saveSession") != null)
              {
                saveSession = request.getParameter("saveSession");
              }

            goTo = VerifyLogin.run(screenName, userPassword, request, response, saveSession);

          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="pachume">

    private void pachume(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User theUser = (User) session.getAttribute("thisUser");
            userBean thisUser = new userBean(theUser.getScreenName());

            if (thisUser.isActive())
              {
                String pachume = null;
                String tags = null;
                int channelId = -1;
                String groupId = "00000000-0000-0000-0000-000000000000";
                String channelName = "home";

                String role = thisUser.getRole();
                boolean privatized = thisUser.isPrivatized();
                String location = thisUser.getLocation();

                if (request.getParameter("pachume") != null && request.getParameter("pachume").length() > 0)
                  {
                    int userId = thisUser.getUserId();
                    pachume = request.getParameter("pachume");
                    if (request.getParameter("tags") != null && request.getParameter("tags").length() > 0)
                      {
                        tags = request.getParameter("tags");
                      }
                    if (request.getParameter("channelId") != null)
                      {
                        try
                          {
                            channelId = Integer.parseInt(request.getParameter("channelId"));
                            channelName = GetChannelName.run(channelId);
                            location = " <a href=\"/channel/" + channelName.trim() + "\" > " + channelName.trim() + " </a> ";
                          } catch (Exception e)
                          {
                            System.out.print(e);
                          }
                      }
                    if (request.getParameter("groupName") != null)
                      {
                        try
                          {
                            String groupName = request.getParameter("groupName");
                            groupId = GetGroupId.run(groupName);
                            location = " <a href=\"/group/" + groupName + "\" > " + groupName + " </a> ";
                          } catch (Exception e)
                          {
                            System.out.print(e);
                          }
                      }
                    int pachumeId = MakePachume.run(userId, pachume, tags, role, privatized, location, channelId, channelName, groupId);
                    if (pachumeId > -1)
                      {
                        if (InsertTags.run(pachumeId, tags))
                          {
                            goTo += "message=Taggs Inserted";
                          }
                      }
                  }
              } else
              {
                goTo = "/login.jsp";
              }
          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }
        this.redirect(request, response, goTo);
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="updateEmail">

    private void updateEmail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);

        try
          {
            String emailAddress = "email";
            int userId = -1;

            if (request.getParameter(emailAddress) != null)
              {
                emailAddress = request.getParameter(emailAddress);

                HttpSession session = request.getSession();
                User loggedUser = (User) session.getAttribute("thisUser");
                userBean thisUser = new userBean(loggedUser.getScreenName());
                userId = thisUser.getUserId();

                if (VerifyEmail.run(emailAddress))
                  {
                    if (IsEmailAvailable.run(emailAddress))
                      {
                        if (command.user.settings.AlterEmail.run(userId, emailAddress))
                          {
                            thisUser = new userBean(loggedUser.getScreenName());
                            Email Email = new EmailUpdatedNotificationEmail(thisUser);
                            if (SendEmail.run(emailAddress, Email.getSubject(), Email.getBody()))
                              {
                                goTo += "message=your Email has been changed";
                              } else
                              {
                                goTo += "message=could not change your email at this time";
                              }
                          } else
                          {
                            goTo += "message=could not change your email at this time";
                          }
                      } else
                      {
                        goTo += "message=that email address is already registered with pachume";
                      }
                  } else
                  {
                    goTo += "message=that email address is already in use or is malformed";
                  }
              }
          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=3");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="updateLocation">

    private void updateLocation(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = new User();

            thisUser = (User) session.getAttribute("thisUser");
            int userId = thisUser.getUserId();
            String location = null;

            if (request.getParameter("location") != null)
              {
                location = request.getParameter("location");

                if (location.length() < 20)
                  {
                    if (command.user.settings.AlterLocation.run(userId, location))
                      {
                        goTo += "message=location updated";
                        thisUser.setLocation(location);
                        session.setAttribute("thisUser", thisUser);
                      } else
                      {
                        goTo += "message=could not update the location at this time";
                      }

                  } else
                  {
                    goTo += "message=please try to keep your location update under 20 characters";
                  }


              } else
              {
                goTo += "message=your location information has been deleted";
              }
          } catch (Exception e)
          {
            goTo += "message=" + e.toString();
          }

        this.redirect(request, response, goTo);

    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="changeBackgroundColor">

    private void changeBackgroundColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goTo = GetGoTo.run(request);
        try
          {
            String backgroundColor = "backgroundColor";
            int userId = -1;

            if (request.getParameter(backgroundColor) != null)
              {
                backgroundColor = request.getParameter(backgroundColor);
              }

            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");
            userId = thisUser.getUserId();

            if (command.user.settings.AlterBackgroundColor.run(userId, backgroundColor))
              {
                thisUser.setBackgroundColor(backgroundColor);
                goTo += "message=background color changed";
              } else
              {
                goTo += "message=could not change the color at this time";
              }

          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="changeBackgroundImageRepeat">

    private void changeBackgroundImageRepeat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goTo = GetGoTo.run(request);
        try
          {
            String backgroundImageRepeat = "backgroundImageRepeat";
            int userId = -1;

            if (request.getParameter(backgroundImageRepeat) != null)
              {
                backgroundImageRepeat = request.getParameter(backgroundImageRepeat);
              } else
              {
                backgroundImageRepeat = "no-repeat";
              }

            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");
            userId = thisUser.getUserId();

            if (AlterBackgroundImageRepeat.run(userId, backgroundImageRepeat))
              {
                thisUser.setBackgroundImageRepeat(backgroundImageRepeat);
                goTo += "message=background repeat changed";

              } else
              {
                goTo += "message=could not manage the repeat at this time";
              }
          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="changeChannelBackgroundColor">

    private void changeChannelBackgroundColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goTo = GetGoTo.run(request);
        try
          {
            String backgroundColor = "backgroundColor";
            String channelName = "channelName";

            HttpSession session = request.getSession();
            channelName = (String) session.getAttribute(channelName);
            channelBean thisChannel = new channelBean(channelName);
            int channelId = thisChannel.getChannelId();

            if (IsUserChannelsAuthor.run(channelId, session))
              {
                if (request.getParameter(backgroundColor) != null)
                  {

                    backgroundColor = request.getParameter(backgroundColor);
                  }

                boolean done = UpdateBackgroundColor.run(channelName, backgroundColor);

                if (done)
                  {
                    goTo += "message=background color changed";
                  } else
                  {
                    goTo += "message=could not change the color at this time";
                  }
              }
          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="changeChannelBackgroundImageRepeat">

    private void changeChannelBackgroundImageRepeat(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {

        String goTo = GetGoTo.run(request);
        try
          {
            String backgroundImageRepeat = "backgroundImageRepeat";
            String channelName = "channelName";

            HttpSession session = request.getSession();
            channelName = (String) session.getAttribute(channelName);
            channelBean thisChannel = new channelBean(channelName);
            int channelId = thisChannel.getChannelId();

            if (IsUserChannelsAuthor.run(channelId, session))
              {

                if (request.getParameter(backgroundImageRepeat) != null)
                  {
                    backgroundImageRepeat = request.getParameter(backgroundImageRepeat);
                  } else
                  {
                    backgroundImageRepeat = "no-repeat";
                  }

                boolean done = UpdateBackgroundImageRepeat.run(channelName, backgroundImageRepeat);

                if (done)
                  {
                    goTo += "message=background repeat changed";

                  } else
                  {
                    goTo += "message=could not manage the repeat at this time";
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="changeGroupBackgroundColor">

    private void changeGroupBackgroundColor(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {


        String goTo = GetGoTo.run(request);
        try
          {
            String backgroundColor = "backgroundColor";
            String groupName = "groupName";

            HttpSession session = request.getSession();
            groupName = (String) session.getAttribute(groupName);
            groupBean thisGroup = new groupBean(groupName);
            String groupId = thisGroup.getGroupId();

            if (IsUserGroupsAuthor.run(groupId, session))
              {
                if (request.getParameter(backgroundColor) != null)
                  {

                    backgroundColor = request.getParameter(backgroundColor);
                  }

                boolean done = UpdateGroupBackgroundColor.run(groupName, backgroundColor);

                if (done)
                  {
                    goTo += "message=background color changed";
                  } else
                  {
                    goTo += "message=could not change the color at this time";
                  }
              }
          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="changeGroupBackgroundImageRepeat">

    private void changeGroupBackgroundImageRepeat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goTo = GetGoTo.run(request);
        try
          {
            String backgroundImageRepeat = "backgroundImageRepeat";
            String groupName = "groupName";

            HttpSession session = request.getSession();
            groupName = (String) session.getAttribute(groupName);
            groupBean thisGroup = new groupBean(groupName);
            String groupId = thisGroup.getGroupId();

            if (IsUserGroupsAuthor.run(groupId, session))
              {

                if (request.getParameter(backgroundImageRepeat) != null)
                  {
                    backgroundImageRepeat = request.getParameter(backgroundImageRepeat);
                  } else
                  {
                    backgroundImageRepeat = "no-repeat";
                  }

                boolean done = UpdateGroupBackgroundImageRepeat.run(groupName, backgroundImageRepeat);

                if (done)
                  {
                    goTo += "message=background repeat changed";

                  } else
                  {
                    goTo += "message=could not manage the repeat at this time";
                  }
              }

          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="changeNotification">

    private void changeNotification(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String goTo = GetGoTo.run(request);
        try
          {
            boolean notifiable = false;

            int userId = -1;

            if (request.getParameter("notifiable") != null)
              {
                if (request.getParameter("notifiable").toString().equals("true"))
                  {
                    notifiable = true;
                  }
              } else
              {
                notifiable = false;
              }

            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");
            userId = thisUser.getUserId();

            if (command.user.settings.AlterNotifiable.run(userId, notifiable))
              {
                thisUser.setNotifiable(notifiable);
                goTo += "message=notification has been changed";

              } else
              {
                goTo += "message=please try again later";
              }
          } catch (Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=3");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="inviteToGroup">

    private void inviteToGroup(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String goTo = GetGoTo.run(request);
        String screenName = null;
        String groupId = null;
        String recipient_Email = null;
        int recipient_userId = -1;
        User loggedUser = null;
        userBean thisUser = null;
        try
          {
            HttpSession session = request.getSession();
            if (session.getAttribute("thisUser") == null)
              {
                goTo += "message=please log in to view this channel";
              } else
              {
                if (request.getParameter("groupId") == null)
                  {
                    goTo += "message=that group does not exist";
                  } else
                  {
                    if (request.getParameter("screenName") == null)
                      {
                        goTo += "message=please enter a valid screen name";
                      } else
                      {
                        screenName = request.getParameter("screenName");
                        if (IsScreenNameAvailable.run(screenName.trim()))
                          {
                            goTo += "message=That screen name is yet registered to a pachume member";
                          } else
                          {
                            groupId = request.getParameter("groupId");
                            loggedUser = (User) session.getAttribute("thisUser");
                            thisUser = new userBean(loggedUser.getScreenName().trim());
                            if (IsUserGroupsAuthor.run(groupId, session))
                              {
                                recipient_userId = GetUserId.run(screenName);
                                recipient_Email = GetEmailFromUserId.run(recipient_userId);
                                Email joinGroupEmail = new JoinGroupRequestEmail(thisUser, recipient_Email.trim(), groupId.trim());
                                SendEmail.run(screenName.trim(), joinGroupEmail.getSubject(), joinGroupEmail.getBody());
                                goTo += "message=An invitation has been sent successfully to join this group";
                              } else
                              {
                                goTo += "message=Only group authors may send group invites";
                              }
                          }
                      }
                  }
              }
          } catch (Exception e)
          {
            System.out.print(e);
          }
        this.redirect(request, response, goTo);
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="uploadBackgroundImage">

    private void uploadBackgroundImage(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            User thisUser = (User) session.getAttribute("thisUser");

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart)
              {
                //checks file size
                if (request.getContentLength() < 1000)
                  {
                    goTo += "message=The image is much too small " + request.getContentLength();//exit if file length is not sufficiently large  
                  } else if (request.getContentLength() > 700000)
                  {
                    goTo += "message=The image is too large try to keep it around 700kb" + request.getContentLength();
                  } else
                  {
// Create a new file upload handler 
                    ServletFileUpload upload = new ServletFileUpload();

                    // Parse the request 
                    FileItemIterator iter = upload.getItemIterator(request);
                    while (iter.hasNext())
                      {
                        FileItemStream item = iter.next();

                        InputStream backgroundStream = new BufferedInputStream(item.openStream());

                        String name = item.getFieldName();

                        if (item.isFormField())
                          {
                            System.out.println("Form field " + name + " with value " + Streams.asString(backgroundStream) + " detected.");
                          } else
                          {
                            System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                            // Process the input stream ... } }

                            Image background = (Image) ImageIO.read(backgroundStream);
                            ByteArrayOutputStream scaledImage = ConvertImageToJPEG.run(background);

                            //Write it to a file
                            FileOutputStream file = new FileOutputStream(request.getRealPath("/") + "media/images/bg_" + thisUser.getScreenName() + ".jpg");
                            scaledImage.writeTo(file);
                            file.flush();
                            file.close();

                          }
                      }

                    UUIDGenerator joe = UUIDGenerator.getInstance();
                    String uuid = joe.generateRandomBasedUUID().toString();

                    String backgroundImageLocation = request.getContextPath() + "/media/images/bg_" + thisUser.getScreenName() + ".jpg?" + uuid;
                    thisUser.setBackgroundImageLocation(backgroundImageLocation);
                    AlterBackgroundImageLocation.run(thisUser.getUserId(), backgroundImageLocation);
                    session.setAttribute("thisUser", thisUser);

                    goTo += "message=your background image has been changed";
                  }
              } else
              {
                goTo += "message=this is not a multipart form";
              }

          } catch (java.lang.Exception e)
          {
            goTo += "message=" + e;
          }

        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="uploadChannelAvatar">

    private void uploadChannelAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String goTo = GetGoTo.run(request);
        try
          {

            HttpSession session = request.getSession();
            channelBean thisChannel = new channelBean((String) session.getAttribute("channelName"));
            int channelId = thisChannel.getChannelId();

            if (IsUserChannelsAuthor.run(channelId, session))
              {

                boolean isMultipart = ServletFileUpload.isMultipartContent(request);

                if (isMultipart)
                  {
                    //checks file size
                    if (request.getContentLength() < 3000)
                      {
                        goTo += "message=The image is much too small " + request.getContentLength();//exit if file length is not sufficiently large  
                      } else if (request.getContentLength() > 700000)
                      {
                        goTo += "message=The image is too large try to keep it around 700kb" + request.getContentLength();
                      } else
                      {
// Create a new file upload handler 
                        ServletFileUpload upload = new ServletFileUpload();

                        // Parse the request 
                        FileItemIterator iter = upload.getItemIterator(request);

                        while (iter.hasNext())
                          {
                            FileItemStream item = iter.next();

                            InputStream avatarStream = new BufferedInputStream(item.openStream());

                            String name = item.getFieldName();
                            if (item.isFormField())
                              {
                                System.out.println("Form field " + name + " with value " + Streams.asString(avatarStream) + " detected.");
                              } else
                              {
                                System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                                // Process the input stream ... } }

                                Image channelAvatar = (Image) ImageIO.read(avatarStream);
                                Image imageContact = channelAvatar;

                                ByteArrayOutputStream channelAvatarImage = ScaleImage.run(channelAvatar, 102, 102);
                                ByteArrayOutputStream channelContactImage = ScaleImage.run(imageContact, 40, 40);

                                //Write it to a file
                                FileOutputStream channelAvatarFile = new FileOutputStream(request.getRealPath("/") + "media/images/channel_avatar_" + thisChannel.getChannelName() + ".png");
                                channelAvatarImage.writeTo(channelAvatarFile);
                                channelAvatarFile.flush();
                                channelAvatarFile.close();

                                FileOutputStream channelContactFile = new FileOutputStream(request.getRealPath("/") + "media/images/channel_contact_" + thisChannel.getChannelName() + ".png");
                                channelContactImage.writeTo(channelContactFile);
                                channelContactFile.flush();
                                channelContactFile.close();
                              }
                          }

                        UUIDGenerator joe = UUIDGenerator.getInstance();
                        String uuid = joe.generateRandomBasedUUID().toString();


                        String channelAvatarLocation = request.getContextPath() + "/media/images/channel_avatar_" + thisChannel.getChannelName() + ".png?" + uuid;
                        String channelContactImageLocation = request.getContextPath() + "/media/images/channel_contact_" + thisChannel.getChannelName() + ".png?" + uuid;

                        UpdateChannelAvatarLocation.run(thisChannel.getChannelId(), channelAvatarLocation, channelContactImageLocation);

                        goTo += "message=avatar uploaded successfully";
                      }
                  } else
                  {
                    goTo += "message=this is not a multipart form";
                  }

              }

          } catch (java.lang.Exception e)
          {
            goTo += "message=" + e;
          }


        this.redirect(request, response, goTo + "&tab=1");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="uploadChannelBackgroundImage">

    private void uploadChannelBackgroundImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goTo = GetGoTo.run(request);
        try
          {

            HttpSession session = request.getSession();
            channelBean thisChannel = new channelBean((String) session.getAttribute("channelName"));
            int channelId = thisChannel.getChannelId();

            if (IsUserChannelsAuthor.run(channelId, session))
              {

                boolean isMultipart = ServletFileUpload.isMultipartContent(request);

                if (isMultipart)
                  {
                    //checks file size
                    if (request.getContentLength() < 1000)
                      {
                        goTo += "message=The image is much too small " + request.getContentLength();//exit if file length is not sufficiently large  
                      } else if (request.getContentLength() > 700000)
                      {
                        goTo += "message=The image is too large try to keep it around 700kb" + request.getContentLength();
                      } else
                      {
// Create a new file upload handler 
                        ServletFileUpload upload = new ServletFileUpload();

                        // Parse the request 
                        FileItemIterator iter = upload.getItemIterator(request);

                        while (iter.hasNext())
                          {
                            FileItemStream item = iter.next();

                            InputStream backgroundStream = new BufferedInputStream(item.openStream());

                            String name = item.getFieldName();
                            if (item.isFormField())
                              {
                                System.out.println("Form field " + name + " with value " + Streams.asString(backgroundStream) + " detected.");
                              } else
                              {
                                System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                                // Process the input stream ... } }

                                Image channelbackground = (Image) ImageIO.read(backgroundStream);
                                ByteArrayOutputStream scaledImage = ConvertImageToJPEG.run(channelbackground);

                                //Write it to a file
                                FileOutputStream file = new FileOutputStream(request.getRealPath("/") + "media/images/channel_bg_" + thisChannel.getChannelName() + ".jpg");
                                scaledImage.writeTo(file);
                                file.flush();
                                file.close();
                              }
                          }

                        UUIDGenerator joe = UUIDGenerator.getInstance();
                        String uuid = joe.generateRandomBasedUUID().toString();

                        String backgroundImageLocation = request.getContextPath() + "/media/images/channel_bg_" + thisChannel.getChannelName() + ".jpg?" + uuid;
                        UpdateBackgroundImageLocation.run(thisChannel.getChannelId(), backgroundImageLocation);

                        goTo += "message=" + thisChannel.getChannelName() + "'s background image has been changed";
                      }
                  } else
                  {
                    goTo += "message=this is not a multipart form";
                  }
              }
          } catch (java.lang.Exception e)
          {
            goTo += "message=" + e;
          }

        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="uploadGroupAvatar">

    private void uploadGroupAvatar(HttpServletRequest request, HttpServletResponse response) throws ServletException, ServletException, IOException {

        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            groupBean thisGroup = new groupBean((String) session.getAttribute("groupName"));
            String groupId = thisGroup.getGroupId();

            if (IsUserGroupsAuthor.run(groupId, session))
              {

                boolean isMultipart = ServletFileUpload.isMultipartContent(request);

                if (isMultipart)
                  {
                    //checks file size
                    if (request.getContentLength() < 1000)
                      {
                        goTo += "message=The image is much too small " + request.getContentLength();//exit if file length is not sufficiently large  
                      } else if (request.getContentLength() > 700000)
                      {
                        goTo += "message=The image is too large try to keep it around 700kb" + request.getContentLength();
                      } else
                      {
// Create a new file upload handler 
                        ServletFileUpload upload = new ServletFileUpload();

                        // Parse the request 
                        FileItemIterator iter = upload.getItemIterator(request);

                        while (iter.hasNext())
                          {
                            FileItemStream item = iter.next();

                            InputStream avatarStream = new BufferedInputStream(item.openStream());

                            String name = item.getFieldName();
                            if (item.isFormField())
                              {
                                System.out.println("Form field " + name + " with value " + Streams.asString(avatarStream) + " detected.");
                              } else
                              {
                                System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                                // Process the input stream ... } }

                                Image groupAvatar = (Image) ImageIO.read(avatarStream);
                                Image imageContact = groupAvatar;

                                ByteArrayOutputStream groupAvatarImage = ScaleImage.run(groupAvatar, 102, 102);
                                ByteArrayOutputStream groupContactImage = ScaleImage.run(imageContact, 40, 40);

                                //Write it to a file
                                FileOutputStream groupAvatarFile = new FileOutputStream(request.getRealPath("/") + "media/images/group_avatar_" + thisGroup.getGroupName() + ".png");
                                groupAvatarImage.writeTo(groupAvatarFile);
                                groupAvatarFile.flush();
                                groupAvatarFile.close();

                                FileOutputStream groupContactFile = new FileOutputStream(request.getRealPath("/") + "media/images/group_contact_" + thisGroup.getGroupName() + ".png");
                                groupContactImage.writeTo(groupContactFile);
                                groupContactFile.flush();
                                groupContactFile.close();
                              }
                          }

                        UUIDGenerator joe = UUIDGenerator.getInstance();
                        String uuid = joe.generateRandomBasedUUID().toString();

                        String groupAvatarLocation = request.getContextPath() + "/media/images/group_avatar_" + thisGroup.getGroupName() + ".png?" + uuid;
                        String groupContactImageLocation = request.getContextPath() + "/media/images/group_contact_" + thisGroup.getGroupName() + ".png?" + uuid;

                        UpdateGroupAvatarLocation.run(thisGroup.getGroupId(), groupAvatarLocation, groupContactImageLocation);

                        goTo += "message=avatar uploaded successfully";
                      }
                  } else
                  {
                    goTo += "message=this is not a multipart form";
                  }

              } else
              {
                goTo += "message=you are not authorized to perform that action";
              }

          } catch (java.lang.Exception e)
          {
            goTo += "message=" + e;
          }

        this.redirect(request, response, goTo + "&tab=1");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="uploadGroupBackgroundImage">

    private void uploadGroupBackgroundImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String goTo = GetGoTo.run(request);
        try
          {
            HttpSession session = request.getSession();
            groupBean thisGroup = new groupBean((String) session.getAttribute("groupName"));
            String groupId = thisGroup.getGroupId();

            if (IsUserGroupsAuthor.run(groupId, session))
              {
                boolean isMultipart = ServletFileUpload.isMultipartContent(request);

                if (isMultipart)
                  {
                    //checks file size
                    if (request.getContentLength() < 1000)
                      {
                        goTo += "message=The image is much too small " + request.getContentLength();//exit if file length is not sufficiently large  
                      } else if (request.getContentLength() > 700000)
                      {
                        goTo += "message=The image is too large try to keep it around 700kb" + request.getContentLength();
                      } else
                      {
// Create a new file upload handler 
                        ServletFileUpload upload = new ServletFileUpload();

                        // Parse the request 
                        FileItemIterator iter = upload.getItemIterator(request);

                        while (iter.hasNext())
                          {
                            FileItemStream item = iter.next();

                            InputStream avatarStream = new BufferedInputStream(item.openStream());

                            String name = item.getFieldName();
                            if (item.isFormField())
                              {
                                System.out.println("Form field " + name + " with value " + Streams.asString(avatarStream) + " detected.");
                              } else
                              {
                                System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                                // Process the input stream ... } }

                                Image groupAvatar = (Image) ImageIO.read(avatarStream);

                                ByteArrayOutputStream groupAvatarImage = ConvertImageToJPEG.run(groupAvatar);

                                //Write it to a file
                                FileOutputStream groupAvatarFile = new FileOutputStream(request.getRealPath("/") + "media/images/group_background_" + thisGroup.getGroupName() + ".jpg");
                                groupAvatarImage.writeTo(groupAvatarFile);
                                groupAvatarFile.flush();
                                groupAvatarFile.close();
                              }
                          }

                        UUIDGenerator joe = UUIDGenerator.getInstance();
                        String uuid = joe.generateRandomBasedUUID().toString();


                        String groupBackgroundLocation = request.getContextPath() + "/media/images/group_background_" + thisGroup.getGroupName() + ".jpg?" + uuid;

                        command.group.UpdateGroupBackgroundImageLocation.run(thisGroup.getGroupId(), groupBackgroundLocation);

                        goTo += "message=avatar uploaded successfully";
                      }
                  } else
                  {
                    goTo += "message=this is not a multipart form";
                  }

              } else
              {
                goTo += "message=you are not authorized to perform that action";
              }

          } catch (java.lang.Exception e)
          {
            goTo += "message=" + e;
          }
        this.redirect(request, response, goTo + "&tab=2");
    }
    // </editor-fold>
//    
    // <editor-fold defaultstate="collapsed" desc="uploadImage">

    private void uploadImage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        String goTo = GetGoTo.run(request);

        HttpSession session = request.getSession();
        User thisUser = (User) session.getAttribute("thisUser");

        try
          {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            if (isMultipart)
              {
                //checks file size
                if (request.getContentLength() < 3000)
                  {
                    goTo += "message=The image is much too small " + request.getContentLength();//exit if file length is not sufficiently large  
                  } else if (request.getContentLength() > 700000)
                  {
                    goTo += "message=The image is too large try to keep it around 700kb" + request.getContentLength();
                  } else
                  {
// Create a new file upload handler 
                    ServletFileUpload upload = new ServletFileUpload();

                    // Parse the request 
                    FileItemIterator iter = upload.getItemIterator(request);

                    while (iter.hasNext())
                      {
                        FileItemStream item = iter.next();

                        InputStream avatarStream = new BufferedInputStream(item.openStream());

                        String name = item.getFieldName();
                        if (item.isFormField())
                          {
                            System.out.println("Form field " + name + " with value " + Streams.asString(avatarStream) + " detected.");
                          } else
                          {
                            System.out.println("File field " + name + " with file name " + item.getName() + " detected.");
                            // Process the input stream ... } }

                            Image imageAvatar = (Image) ImageIO.read(avatarStream);
                            Image imagePachume = imageAvatar;
                            Image imageContact = imagePachume;

                            ByteArrayOutputStream avatarImage = ScaleImage.run(imageAvatar, 102, 102);
                            ByteArrayOutputStream pachumeImage = ScaleImage.run(imagePachume, 40, 40);
                            ByteArrayOutputStream contactImage = ScaleImage.run(imageContact, 27, 27);

                            //Write it to a file
                            FileOutputStream avatarFile = new FileOutputStream(request.getRealPath("/") + "media/images/avatar_" + thisUser.getScreenName() + ".png");
                            avatarImage.writeTo(avatarFile);
                            avatarFile.flush();
                            avatarFile.close();

                            FileOutputStream pachumeFile = new FileOutputStream(request.getRealPath("/") + "media/images/pachume_" + thisUser.getScreenName() + ".png");
                            pachumeImage.writeTo(pachumeFile);
                            pachumeFile.flush();
                            pachumeFile.close();

                            FileOutputStream contactFile = new FileOutputStream(request.getRealPath("/") + "media/images/contact_" + thisUser.getScreenName() + ".png");
                            contactImage.writeTo(contactFile);
                            contactFile.flush();
                            contactFile.close();
                          }
                      }

                    UUIDGenerator joe = UUIDGenerator.getInstance();
                    String uuid = joe.generateRandomBasedUUID().toString();


                    String avatarLocation = request.getContextPath() + "/media/images/avatar_" + thisUser.getScreenName() + ".png?" + uuid;
                    thisUser.setAvatarLocation(avatarLocation);

                    String pachumeImageLocation = request.getContextPath() + "/media/images/pachume_" + thisUser.getScreenName() + ".png?" + uuid;
                    thisUser.setPachumeImageLocation(pachumeImageLocation);

                    String contactImageLocation = request.getContextPath() + "/media/images/contact_" + thisUser.getScreenName() + ".png?" + uuid;
                    thisUser.setContactImageLocation(contactImageLocation);

                    AlterAvatarLocation.run(thisUser.getUserId(), avatarLocation, pachumeImageLocation, contactImageLocation);

                    session.setAttribute("thisUser", thisUser);
                    goTo += "message=image upload successfull";
                  }
              } else
              {
                goTo += "message=this is not a multipart form";
              }

          } catch (java.lang.Exception e)
          {
            goTo += "message=" + e;
          }

        this.redirect(request, response, goTo + "&tab=1");
    }
    // </editor-fold>
//    
//
    // <editor-fold defaultstate="collapsed" desc="getServletInfo">

    /**
     * Returns a short description of the servlet.
     */
    @Override
    public String getServletInfo() {
        return "Front Controller for pachume.com";
    }    // </editor-fold>
}
