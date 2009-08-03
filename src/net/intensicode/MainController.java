package net.intensicode;

import net.intensicode.core.*;
import net.intensicode.screens.*;
import net.intensicode.util.*;

import javax.microedition.lcdui.*;


public final class MainController extends AbstractScreen implements LoadingCallback
    {
    public MainController()
        {
        }

    // From LoadingCallback

    public final void onLoadingDone( final Engine aEngine, final DirectScreen aScreen ) throws Exception
        {
        if ( myLateInitFlag ) return;
        myLateInitFlag = true;

        //#if CONSOLE
        aEngine.addGlobalHandler( new ConsoleOverlay( mySkin.font( "textfont" ) ) );
        //#endif
        }

    // From AbstractScreen

    public void onInitOnce( final Engine aEngine, final DirectScreen aScreen ) throws Exception
        {
        final ResourceLoader loader = aEngine.loader;
        final Configuration skinConfig = loader.loadConfiguration( "/skin.properties" );
        mySkin = new Skin( loader, skinConfig );

        mySharedSoftkeys = new AutoHideSoftkeysScreen( mySkin.font( "textfont" ) );
        }

    public final void onControlTick( final Engine aEngine ) throws Exception
        {
        showLoadingScreen( aEngine );
        }

    public final void onDrawFrame( final DirectScreen aScreen )
        {
        }

    // Implementation

    private void showLoadingScreen( final Engine aEngine ) throws Exception
        {
        final BitmapFontGen textFont = mySkin.font( "textfont" );
        final LoadingScreen loadingScreen = new LoadingScreen( mySkin, textFont );
        loadingScreen.shareSoftkeys( mySharedSoftkeys );
        loadingScreen.setLateInit( this );

        final Image logoImage = mySkin.image( "logo" );
        final ImageScreen logo = new ImageScreen( logoImage );
        logo.position.x = 50;
        logo.position.y = 50;
        loadingScreen.setLogo( logo );

        aEngine.pushOnce( loadingScreen );
        }



    private Skin mySkin;

    private boolean myLateInitFlag;

    private AutoHideSoftkeysScreen mySharedSoftkeys;
    }
