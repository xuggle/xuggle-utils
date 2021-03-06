/*******************************************************************************
 * Copyright (c) 2008, 2010 Xuggle Inc.  All rights reserved.
 *  
 * This file is part of Xuggle-Utils.
 *
 * Xuggle-Utils is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Xuggle-Utils is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Xuggle-Utils.  If not, see <http://www.gnu.org/licenses/>.
 *******************************************************************************/

package com.xuggle.utils.event.handler;

import com.xuggle.utils.event.IEvent;
import com.xuggle.utils.event.IEventDispatcher;
import com.xuggle.utils.event.IEventHandler;

/**
 * A ForwardingHandler will take an event it is registered for,
 * and forward it to another {@link IEventDispatcher}.
 * 
 * <p>
 * 
 * In the event that the other {@link IEventDispatcher} is the same
 * as the {@link IEventDispatcher} that this class's
 * {@link #handleEvent(IEventDispatcher, IEvent)} is called on,
 * this handler will do nothing.
 * 
 * @author aclarke
 *
 */
public class ForwardingHandler implements IEventHandler<IEvent>
{
  private final IEventDispatcher mDispatcher;
  
  /**
   * Creates a new object that will just forward to aDispatcher. 
   * 
   * @param aDispatcher The dispatcher to forward events to.  The user
   *   of this object is responsible for ensuring the destination dispatcher
   *   has any required handlers registered.
   */
  
  ForwardingHandler(IEventDispatcher aDispatcher)
  {
    if (aDispatcher == null)
      throw new IllegalArgumentException("need dispatcher to dispatch to");
    
    mDispatcher = aDispatcher;
  }
  
  /**
   * Return the dispatcher we will forward events to
   * @return the dispatcher we forward events to
   */
  
  public IEventDispatcher getDestinationDispatcher()
  {
    return mDispatcher;
  }
  
  /**
   * Dispatches this event to the {@link #getDestinationDispatcher()},
   * unless aDispatcher is the same as {@link #getDestinationDispatcher()},
   * in which case we do nothing.
   * 
   * <p>
   * 
   * {@inheritDoc}
   * 
   * </p>
   */
  
  public boolean handleEvent(IEventDispatcher aDispatcher, IEvent aEvent)
  {
    if (aDispatcher != mDispatcher)
      mDispatcher.dispatchEvent(aEvent);
    return false;
  }

}
