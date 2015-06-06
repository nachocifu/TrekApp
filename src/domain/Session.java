package domain;

import controllers.Application;

public class Session {

    /* Singleton Session Reference*/
    private static Session session = null;

    /* Current user's username*/
    private String userName = null;

    /* Signals if there is session in progress */
    private Boolean isActive = false;

    /* Signals if busy waiting for a response */
    private Boolean waitingForCallBack = false;

    /**
     * Private constructor for singleton implementation.
     */
    private Session(){

    }

    /**
     * Returns the singleton session.
     *
     * @return
     */
    public static Session getInstance() {
        if( session == null)
            session = new Session();
        return session;
    }

    /**
     * Symbolic method for logging out.
     * Changes current credentials to null
     */
    public void logOut() {
        this.userName = null;
        this.waitingForCallBack = false;
        this.isActive = false;
    }

    /**
     * Validate credentials. If legitimate store them else discard them.
     *
     * @param userName The useName with which to login
     * @param passWord The passWord with which to login
     * @return boolean Indicate if successful login
     */
    public Boolean logIn(String userName, String passWord) {
        /* Close any open session */
        this.logOut();

        /* Validate Credentials */
        this.waitingForCallBack = true;

        if (Application.getInstance().validate( userName, passWord )){
            this.waitingForCallBack = false;
            this.userName = userName;
            this.isActive = true;
            return true;
        }
        else{
            this.logOut();
            return false;
        }
    }

    /**
     *  Used to pause execution until server response arrives.
     *  If not waiting for responses then does nothing.
     *
     */
    private void waitForCallBack(){
        while(this.waitingForCallBack){
        }
    }

    /**
     * Return the currently logged in user.
     *
     * @return userName The username of the current user
     */
    public String getUserName(){
        this.waitForCallBack();
        return this.userName;
    }

    public Boolean isActive() {
        return isActive;
    }

    /*
     * NUNCA  SE DEVUELVE LA CONTRASENA  Y SOLO MANDA POR PARAMETROS A LA HORA DE VALIDAR PQ
     * EL FACTORY ES STATIC, NO SE PUEDE TRUCHAR. EN CUALQUIER OTRA CIRCUNSTANCIA BUSCAR ALTERNATIVAS
     * PASSWORD NO TIENE QUE SALIR NUNCA DE SESSION Y MUCHO MENOS DEL FACOTORY.
     */

}
