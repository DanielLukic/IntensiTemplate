package net.intensicode;

import net.intensicode.core.*;


public final class IntensiTemplate extends IntensiGame
    {
    public IntensiTemplate() throws Exception
        {
        super();
        }

    // From SystemContext

    public final AbstractScreen initMainController() throws Exception
        {
        return new MainController();
        }
    }
