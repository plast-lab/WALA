/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package com.ibm.wala.logic;

import java.util.Collection;

import com.ibm.wala.util.collections.HashSetFactory;

public class CombinedVocabulary extends AbstractVocabulary<IConstant> {

  private final IVocabulary a;
  private final IVocabulary b;
  
  private CombinedVocabulary(IVocabulary a, IVocabulary b) {
    this.a = a;
    this.b = b;
    if (b.getRelations() == null) {
      throw new IllegalArgumentException("b relations are null " + b.getClass());
    }
  }
  
  public static CombinedVocabulary make(IVocabulary a, IVocabulary b) throws IllegalArgumentException {
    if (b == null) {
      throw new IllegalArgumentException("b == null");
    }
    return new CombinedVocabulary(a,b);
  }
  

  @SuppressWarnings("unchecked")
  public Collection<? extends IFunction> getFunctions() {
    Collection<? extends IFunction> s = HashSetFactory.make();
    s.addAll(a.getFunctions());
    s.addAll(b.getFunctions());
    return s;
  }

  @SuppressWarnings("unchecked")
  public Collection<? extends IRelation> getRelations() {
    Collection<? extends IRelation> s = HashSetFactory.make();
    s.addAll(a.getRelations());
    s.addAll(b.getRelations());
    return s;
  }

  @SuppressWarnings("unchecked")
  public Collection<IConstant> getConstants() {
    Collection<IConstant> ma = a.getConstants();
    Collection<IConstant> mb = b.getConstants();
    assert ma != null;
    assert mb != null;
    Collection<IConstant> result = HashSetFactory.make();
    result.addAll(ma);
    result.addAll(mb);
    return result;
  }
}
