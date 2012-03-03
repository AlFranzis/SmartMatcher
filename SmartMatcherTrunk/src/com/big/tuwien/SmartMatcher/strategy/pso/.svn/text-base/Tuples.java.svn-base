/*
 * This file is part of Howard Lovatt's PhD Thesis:
 *     "A Pattern Enforcing Compiler (PEC) for Java".
 * Copyright (C) 2003-2007 Howard Lovatt
 * PEC is trademarked by Howard Lovatt and Java is trademarked by Sun Microsystems
 * More info: pec.dev.java.net
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * You can contact Howard Lovatt at howard.lovatt@iee.org
 * or at 12 Roseberry St., Balmain, NSW, Australia, 2041.
 */
package com.big.tuwien.SmartMatcher.strategy.pso;

import java.util.*;
import static java.util.Arrays.*;

/**
 * Provides a set of static methods for creating and manipulating tuples and a
 * set of classes for representing tuples, up to fifth order. This class, <code>
 * Tuples</code>, is meant to be statically imported so that all the methods and
 * all the classes are imported into the name space of the application, thus
 * saving having to qualify everything with <code>Tuples</code>.
 *
 * <p>Tuples are created concisely using the overloaded method <code>t</code>
 * which accepts up to five arguments and returns the appropriate tuple, e.g. a
 * method might <code>return t( 1, 2, 3 );</code>. The values in the tuples are
 * final fields called <code>e0, e1, ..., e4</code>.</p>
 *
 * <p>Typicall uses are:</p>
 *
 * <pre>
  import static tuples.Tuples.*;

  ...

  public static void main( final String[] notUsed ) &#123;
    final T4&lt;Integer, Integer, Integer, Integer&gt; t4 = t( 0, 1, 2, 3 );
    final List&lt;Integer&gt; l4 = asList( 0, 1, 2, 3 );
    out.println( "t4 = " + t4 + ", l4 = " + l4 );
    out.println( "t4.hashCode() = " + t4.hashCode() + ", l4.hashCode() = " + l4.hashCode() );
    out.println( "t4.equals( l4 ) = " + t4.equals( l4 ) );
    out.println( "t4.tail().equals( l4.subList( 1, 4 ) ) = " + t4.tail().equals( l4.subList( 1, 4 ) ) );
    out.println( "t4.rotate4() = " + t4.rotate4() );
    final List&lt;T2&lt;Object, Integer&gt;&gt; z4 = zip( t4, l4 );
    out.println( "zip( t4, l4 ) = " + z4 );
    out.println( "unzip( z4 ) = " + unzip( z4 ) );
    out.println( "map( t( 1, 'A' ), t( 2, 'B' ) ) = " + map( t( 1, 'A' ), t( 2, 'B' ) ) );
    out.println( "swap( 0, 1 ) = " + swap( 0, 1 ) );
  &#125;

  // Standard swap example - would use rotate2 in T2 in practice
  private static &lt;E0, E1&gt; T2&lt;E1, E0&gt; swap( E0 in0, E1 in1 ) &#123;
    return t( in1, in0 );
  &#125;
 * </pre>
 *
 * <p>Which outputs:</p>
 *
 * <pre>
  t4 = [0, 1, 2, 3], l4 = [0, 1, 2, 3]
  t4.hashCode() = 924547, l4.hashCode() = 924547
  t4.equals( l4 ) = true
  t4.tail().equals( l4.subList( 1, 4 ) ) = true
  t4.rotate4() = [3, 0, 1, 2]
  zip( t4, l4 ) = [[0, 0], [1, 1], [2, 2], [3, 3]]
  unzip( z4 ) = [[0, 1, 2, 3], [0, 1, 2, 3]]
  map( t( 1, 'A' ), t( 2, 'B' ) ) = &#123;1=A, 2=B&#125;
  swap( 0, 1 ) = [1, 0]
 * </pre>
 *
 * <p>A tuples are available in many programming languages, but not Java, as
 * built in features. However this library is more powerful than typical built
 * in tuples, in particular:</p>
 *
 * <ul>
 *   <li>
 *     <p>The tuples are a List of Objects</p>
 *   </li>
 *   <li>
 *     <p>A T5 is assignable to a T4, a T4 to a T3, a T3 to a T2, and a T2 to a
 *     T1</p>
 *   </li>
 *   <li>
 *     <p>You can extract the tail of a T5 as a T4, a T4 as a T3, a T3, as a T2,
 *     and a T2 as a T1</p>
 *   </li>
 *   <li>
 *     <p>You can extend a Tuple and turn it into a class</p>
 *   </li>
 * </ul>
 *
 * <p>To expand upon the last point above: if tuples are used internally in an
 * API and then, as requirements change, you need to make public the internal
 * representations then a good technique is:</p>
 *
 * <pre>
  class Point2D extends T2&lt;Integer, Integer&gt; &#123;
    Point2D( Integer x, Integer y ) &#123; super( x, y ); &#125;
  &#125;
 * </pre>
 *
 * <p>Now suppose you need to add a 3D point and compare 3D with 2D via creating
 * a 2D from a 3D:</p>
 *
 * <pre>
  class Point3D extends T3&lt;Integer, Integer, Integer&gt; &#123;
    Point3D( Integer x, Integer y, Integer z ) &#123; super( x, y, z ); &#125;
    Point2D to2D() &#123;
      if ( e3 != 0 ) &#123; throw new IllegalStateException(); &#125;
      return new Point2D( e0, e1 );
    &#125;
  &#125;
 * </pre>
 */
public class Tuples {
  private Tuples() {}

  /**
   * Convenience method for making a T1.
   *
   * @param   <E0>  Type of first element
   * @param   e0    Value of first element
   *
   * @return  A new T1 containing e0
   */
  public static <E0> T1<E0> t( final E0 e0 ) { return new T1<E0>( e0 ); }

  /**
   * Convenience method for making a T2.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   e0    Value of first element
   * @param   e1    Value of second element
   *
   * @return  A new T2 containing e0 and e1
   */
  public static <E0, E1> T2<E0, E1> t( final E0 e0, final E1 e1 ) {
    return new T2<E0, E1>( e0, e1 );
  }

  /**
   * Convenience method for making a T3.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   e0    Value of first element
   * @param   e1    Value of second element
   * @param   e2    Value of third element
   *
   * @return  A new T3 containing e0, e1, and e2
   */
  public static <E0, E1, E2> T3<E0, E1, E2> t(
                                              final E0 e0, final E1 e1,
                                              final E2 e2 ) {
    return new T3<E0, E1, E2>( e0, e1, e2 );
  }

  /**
   * Convenience method for making a T4.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   e0    Value of first element
   * @param   e1    Value of second element
   * @param   e2    Value of third element
   * @param   e3    Value of forth element
   *
   * @return  A new T4 containing e0, e1, e2, and e3
   */
  public static <E0, E1, E2, E3> T4<E0, E1, E2, E3> t(
                                                      final E0 e0, final E1 e1,
                                                      final E2 e2,
                                                      final E3 e3 ) {
    return new T4<E0, E1, E2, E3>( e0, e1, e2, e3 );
  }

  /**
   * Convenience method for making a T4.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   <E4>  Type of fifth element
   * @param   e0    Value of first element
   * @param   e1    Value of second element
   * @param   e2    Value of third element
   * @param   e3    Value of forth element
   * @param   e4    Value of fifth element
   *
   * @return  A new T5 containing e0, e1, e2, e3, and e4
   */
  public static <E0, E1, E2, E3, E4> T5<E0, E1, E2, E3, E4> t(
                                                              final E0 e0,
                                                              final E1 e1,
                                                              final E2 e2,
                                                              final E3 e3,
                                                              final E4 e4 ) {
    return new T5<E0, E1, E2, E3, E4>( e0, e1, e2, e3, e4 );
  }

  /**
   * Convert T2s into a map.
   *
   * @param   <E0>  Type of key
   * @param   <E1>  Type of value
   * @param   t2s   Key value pairs; either as a var arg list or as an array
   *
   * @return  A LinkedHashMap of the key value pairs
   */
  public static <E0, E1> Map<E0, E1> map( final T2<E0, E1>... t2s ) {
    return map( asList( t2s ) );
  }

  /**
   * Convert T2s into a map.
   *
   * @param   <E0>  Type of key
   * @param   <E1>  Type of value
   * @param   t2s   Key value pairs; as an Iterable, e.g. List
   *
   * @return  A LinkedHashMap of the key value pairs
   */
  public static <E0, E1> Map<E0, E1> map( final Iterable<T2<E0, E1>> t2s ) {
    return map( t2s.iterator() );
  }

  /**
   * Convert T2s into a map.
   *
   * @param   <E0>  Type of key
   * @param   <E1>  Type of value
   * @param   t2s   Key value pairs; as an Iterator
   *
   * @return  A LinkedHashMap of the key value pairs
   */
  public static <E0, E1> Map<E0, E1> map( final Iterator<T2<E0, E1>> t2s ) {
    final Map<E0, E1> result = new LinkedHashMap<E0, E1>();
    while ( t2s.hasNext() ) {
      final T2<E0, E1> t2 = t2s.next();
      result.put( t2.e0, t2.e1 );
    }
    return result;
  }

  /**
   * Unzip T2s into two Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   t2s   T2s; either as a var arg list or as an array
   *
   * @return  Returns two ArrayLists inside a T2, the first contains the e0s and
   *          the second the e1s
   *
   * @throws  NullPointerException  Thrown if t2s is null
   */
  public static <E0, E1> T2<List<E0>, List<E1>> unzip( final T2<E0, E1>... t2s )
      throws NullPointerException { return unzip( asList( t2s ) ); }

  /**
   * Unzip T2s into two Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   t2s   T2s; as an Iterable, e.g. List
   *
   * @return  Returns two ArrayLists inside a T2, the first contains the e0s and
   *          the second the e1s
   *
   * @throws  NullPointerException  Thrown if t2s is null
   */
  public static <E0, E1> T2<List<E0>, List<E1>> unzip(
                                                      final Iterable<T2<E0, E1>> t2s )
      throws NullPointerException { return unzip( t2s.iterator() ); }

  /**
   * Unzip T2s into two Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   t2s   T2s; as an Iterator
   *
   * @return  Returns two ArrayLists inside a T2, the first contains the e0s and
   *          the second the e1s
   *
   * @throws  NullPointerException  Thrown if t2s is null
   */
  public static <E0, E1> T2<List<E0>, List<E1>> unzip(
                                                      final Iterator<T2<E0, E1>> t2s )
      throws NullPointerException {
    final List<E0> l0 = new ArrayList<E0>();
    final List<E1> l1 = new ArrayList<E1>();
    while ( t2s.hasNext() ) {
      final T2<E0, E1> t2 = t2s.next();
      l0.add( t2.e0 );
      l1.add( t2.e1 );
    }
    return t( l0, l1 );
  }

  /**
   * Unzip T3s into three Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   t3s   T3s; either as a var arg list or as an array
   *
   * @return  Returns three ArrayLists inside a T3, the first contains the e0s,
   *          second e1s, and third e2s
   *
   * @throws  NullPointerException  Thrown if t3s is null
   */
  public static <E0, E1, E2> T3<List<E0>, List<E1>, List<E2>> unzip(
                                                                    final T3<E0, E1, E2>... t3s )
      throws NullPointerException { return unzip( asList( t3s ) ); }

  /**
   * Unzip T3s into three Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   t3s   T3s; as an Iterable, e.g. List
   *
   * @return  Returns three ArrayLists inside a T3, the first contains the e0s,
   *          second e1s, and third e2s
   *
   * @throws  NullPointerException  Thrown if t3s is null
   */
  public static <E0, E1, E2> T3<List<E0>, List<E1>, List<E2>> unzip(
                                                                    final Iterable<T3<E0,
                                                                                      E1,
                                                                                      E2>> t3s )
      throws NullPointerException { return unzip( t3s.iterator() ); }

  /**
   * Unzip T3s into three Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   t3s   T3s; as an Iterator
   *
   * @return  Returns three ArrayLists inside a T3, the first contains the e0s,
   *          second e1s, and third e2s
   *
   * @throws  NullPointerException  Thrown if t3s is null
   */
  public static <E0, E1, E2> T3<List<E0>, List<E1>, List<E2>> unzip(
                                                                    final Iterator<T3<E0,
                                                                                      E1,
                                                                                      E2>> t3s )
      throws NullPointerException {
    final List<E0> l0 = new ArrayList<E0>();
    final List<E1> l1 = new ArrayList<E1>();
    final List<E2> l2 = new ArrayList<E2>();
    while ( t3s.hasNext() ) {
      final T3<E0, E1, E2> t3 = t3s.next();
      l0.add( t3.e0 );
      l1.add( t3.e1 );
      l2.add( t3.e2 );
    }
    return t( l0, l1, l2 );
  }

  /**
   * Unzip T4s into four Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   t4s   T4s; either as a var arg list or as an array
   *
   * @return  Returns four ArrayLists inside a T4, the first contains the e0s,
   *          second e1s, third e2s, and forth e3s
   *
   * @throws  NullPointerException  Thrown if t4s is null
   */
  public static <E0, E1, E2, E3> T4<List<E0>, List<E1>, List<E2>, List<E3>>
  unzip( final T4<E0, E1, E2, E3>... t4s ) throws NullPointerException {
    return unzip( asList( t4s ) );
  }

  /**
   * Unzip T4s into four Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   iT    t4s T4s; as an Iterable, e.g. List
   *
   * @return  Returns four ArrayLists inside a T4, the first contains the e0s,
   *          second e1s, third e2s, and forth e3s
   *
   * @throws  NullPointerException  Thrown if t4s is null
   */
  public static <E0, E1, E2, E3> T4<List<E0>, List<E1>, List<E2>, List<E3>>
  unzip( final Iterable<T4<E0, E1, E2, E3>> iT ) throws NullPointerException {
    return unzip( iT.iterator() );
  }

  /**
   * Unzip T4s into four Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   iT    t4s T4s; as an Iterator
   *
   * @return  Returns four ArrayLists inside a T4, the first contains the e0s,
   *          second e1s, third e2s, and forth e3s
   *
   * @throws  NullPointerException  Thrown if t4s is null
   */
  public static <E0, E1, E2, E3> T4<List<E0>, List<E1>, List<E2>, List<E3>>
  unzip( final Iterator<T4<E0, E1, E2, E3>> iT ) throws NullPointerException {
    final List<E0> l0 = new ArrayList<E0>();
    final List<E1> l1 = new ArrayList<E1>();
    final List<E2> l2 = new ArrayList<E2>();
    final List<E3> l3 = new ArrayList<E3>();
    while ( iT.hasNext() ) {
      final T4<E0, E1, E2, E3> t4 = iT.next();
      l0.add( t4.e0 );
      l1.add( t4.e1 );
      l2.add( t4.e2 );
      l3.add( t4.e3 );
    }
    return t( l0, l1, l2, l3 );
  }

  /**
   * Unzip T5s into five Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   <E4>  Type of fifth element
   * @param   t5s   T5s; either as a var arg list or as an array
   *
   * @return  Returns five ArrayLists inside a T5, the first contains the e0s,
   *          second e1s, third e2s, forth e3s, and fifth e4s
   *
   * @throws  NullPointerException  Thrown if t5s is null
   */
  public static <E0, E1, E2, E3, E4> T5<List<E0>,
                                        List<E1>, List<E2>, List<E3>, List<E4>>
  unzip( final T5<E0, E1, E2, E3, E4>... t5s ) throws NullPointerException {
    return unzip( asList( t5s ) );
  }

  /**
   * Unzip T5s into five Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   <E4>  Type of fifth element
   * @param   iT    t5s T5s; as an Iterable, e.g. List
   *
   * @return  Returns five ArrayLists inside a T5, the first contains the e0s,
   *          second e1s, third e2s, forth e3s, and fifth e4s
   *
   * @throws  NullPointerException  Thrown if t5s is null
   */
  public static <E0, E1, E2, E3, E4> T5<List<E0>,
                                        List<E1>, List<E2>, List<E3>, List<E4>>
  unzip( final Iterable<T5<E0, E1, E2, E3, E4>> iT )
      throws NullPointerException { return unzip( iT.iterator() ); }

  /**
   * Unzip T5s into five Lists.
   *
   * @param   <E0>  Type of first element
   * @param   <E1>  Type of second element
   * @param   <E2>  Type of third element
   * @param   <E3>  Type of fourth element
   * @param   <E4>  Type of fifth element
   * @param   t5s   T5s; as an Iterator
   *
   * @return  Returns five ArrayLists inside a T5, the first contains the e0s,
   *          second e1s, third e2s, forth e3s, and fifth e4s
   *
   * @throws  NullPointerException  Thrown if t5s is null
   */
  public static <E0, E1, E2, E3, E4> T5<List<E0>,
                                        List<E1>, List<E2>, List<E3>, List<E4>>
  unzip( final Iterator<T5<E0, E1, E2, E3, E4>> t5s )
      throws NullPointerException {
    final List<E0> l0 = new ArrayList<E0>();
    final List<E1> l1 = new ArrayList<E1>();
    final List<E2> l2 = new ArrayList<E2>();
    final List<E3> l3 = new ArrayList<E3>();
    final List<E4> l4 = new ArrayList<E4>();
    while ( t5s.hasNext() ) {
      final T5<E0, E1, E2, E3, E4> t5 = t5s.next();
      l0.add( t5.e0 );
      l1.add( t5.e1 );
      l2.add( t5.e2 );
      l3.add( t5.e3 );
      l4.add( t5.e4 );
    }
    return t( l0, l1, l2, l3, l4 );
  }

  /**
   * Zip two lists into a single list of T2s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   e0s   First elements; as an Iterable, e.g. List
   * @param   e1s   Second elements; as an Iterable, e.g. List
   *
   * @return  Return an ArrayList of T2s, e0s go into T2s first element and e1s
   *          into the second
   *
   * @throws  NullPointerException  Thrown if either e0s or e1s is null
   */
  public static <E0, E1> List<T2<E0, E1>> zip(
                                              final Iterable<E0> e0s,
                                              final Iterable<E1> e1s )
      throws NullPointerException {
    return zip( e0s.iterator(), e1s.iterator() );
  }

  /**
   * Zip two lists into a single list of T2s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   e0s   First elements; as an Iterator
   * @param   e1s   Second elements; as an Iterator
   *
   * @return  Return an ArrayList of T2s, e0s go into T2s first element and e1s
   *          into the second
   *
   * @throws  NullPointerException  Thrown if either e0s or e1s is null
   */
  public static <E0, E1> List<T2<E0, E1>> zip(
                                              final Iterator<E0> e0s,
                                              final Iterator<E1> e1s )
      throws NullPointerException {
    final ArrayList<T2<E0, E1>> result = new ArrayList<T2<E0, E1>>();
    while ( e0s.hasNext() && e1s.hasNext() ) {
      result.add( t( e0s.next(), e1s.next() ) );
    }
    return result;
  }

  /**
   * Zip three lists into a single list of T3s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   <E2>  Type of third list
   * @param   e0s   First elements; as an Iterable, e.g. List
   * @param   e1s   Second elements; as an Iterable, e.g. List
   * @param   e2s   Third elements; as an Iterable, e.g. List
   *
   * @return  Return an ArrayList of T3s, e0s go into T2s first element, e1s
   *          into the second, and e2s into the third
   *
   * @throws  NullPointerException  Thrown if any of e0s, e1s, or e2s is null
   */
  public static <E0, E1, E2> List<T3<E0, E1, E2>> zip(
                                                      final Iterable<E0> e0s,
                                                      final Iterable<E1> e1s,
                                                      final Iterable<E2> e2s )
      throws NullPointerException {
    return zip( e0s.iterator(), e1s.iterator(), e2s.iterator() );
  }

  /**
   * Zip three lists into a single list of T3s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   <E2>  Type of third list
   * @param   e0s   First elements; as an Iterator
   * @param   e1s   Second elements; as an Iterator
   * @param   e2s   Third elements; as an Iterator
   *
   * @return  Return an ArrayList of T3s, e0s go into T2s first element, e1s
   *          into the second, and e2s into the third
   *
   * @throws  NullPointerException  Thrown if any of e0s, e1s, or e2s is null
   */
  public static <E0, E1, E2> List<T3<E0, E1, E2>> zip(
                                                      final Iterator<E0> e0s,
                                                      final Iterator<E1> e1s,
                                                      final Iterator<E2> e2s )
      throws NullPointerException {
    final ArrayList<T3<E0, E1, E2>> result = new ArrayList<T3<E0, E1, E2>>();
    while ( e0s.hasNext() && e1s.hasNext() && e2s.hasNext() ) {
      result.add( t( e0s.next(), e1s.next(), e2s.next() ) );
    }
    return result;
  }

  /**
   * Zip four lists into a single list of T4s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   <E2>  Type of third list
   * @param   <E3>  Type of fourth list
   * @param   e0s   First elements; as an Iterable, e.g. List
   * @param   e1s   Second elements; as an Iterable, e.g. List
   * @param   e2s   Third elements; as an Iterable, e.g. List
   * @param   e3s   Fourth elements; as an Iterable, e.g. List
   *
   * @return  Return an ArrayList of T4s, e0s go into T2s first element, e1s
   *          into the second, e2s into the third, and e3s into the forth
   *
   * @throws  NullPointerException  Thrown if any of e0s, e1s, e2s, or e3s is
   *                                null
   */
  public static <E0, E1, E2, E3> List<T4<E0, E1, E2, E3>> zip(
                                                              final Iterable<E0> e0s,
                                                              final Iterable<E1> e1s,
                                                              final Iterable<E2> e2s,
                                                              final Iterable<E3> e3s )
      throws NullPointerException {
    return zip(
               e0s.iterator(), e1s.iterator(), e2s.iterator(), e3s.iterator() );
  }

  /**
   * Zip four lists into a single list of T4s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   <E2>  Type of third list
   * @param   <E3>  Type of fourth list
   * @param   e0s   First elements; as an Iterator
   * @param   e1s   Second elements; as an Iterator
   * @param   e2s   Third elements; as an Iterator
   * @param   e3s   Fourth elements; as an Iterator
   *
   * @return  Return an ArrayList of T4s, e0s go into T2s first element, e1s
   *          into the second, e2s into the third, and e3s into the forth
   *
   * @throws  NullPointerException  Thrown if any of e0s, e1s, e2s, or e3s is
   *                                null
   */
  public static <E0, E1, E2, E3> List<T4<E0, E1, E2, E3>> zip(
                                                              final Iterator<E0> e0s,
                                                              final Iterator<E1> e1s,
                                                              final Iterator<E2> e2s,
                                                              final Iterator<E3> e3s )
      throws NullPointerException {
    final ArrayList<T4<E0, E1, E2, E3>> result =
      new ArrayList<T4<E0, E1, E2, E3>>();
    while ( e0s.hasNext() && e1s.hasNext() && e2s.hasNext() && e3s.hasNext() ) {
      result.add( t( e0s.next(), e1s.next(), e2s.next(), e3s.next() ) );
    }
    return result;
  }

  /**
   * Zip five lists into a single list of T5s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   <E2>  Type of third list
   * @param   <E3>  Type of fourth list
   * @param   <E4>  Type of fifth list
   * @param   e0s   First elements; as an Iterable, e.g. List
   * @param   e1s   Second elements; as an Iterable, e.g. List
   * @param   e2s   Third elements; as an Iterable, e.g. List
   * @param   e3s   Fourth elements; as an Iterable, e.g. List
   * @param   e4s   Fifth elements; as an Iterable, e.g. List
   *
   * @return  Return an ArrayList of T5s, e0s go into T2s first element, e1s
   *          into the second, e2s into the third, e3s into the forth, and e4s
   *          into the fifth
   *
   * @throws  NullPointerException  Thrown if any of e0s, e1s, e2s, e3s, or e4s
   *                                is null
   */
  public static <E0, E1, E2, E3, E4> List<T5<E0, E1, E2, E3, E4>> zip(
                                                                      final Iterable<E0> e0s,
                                                                      final Iterable<E1> e1s,
                                                                      final Iterable<E2> e2s,
                                                                      final Iterable<E3> e3s,
                                                                      final Iterable<E4> e4s )
      throws NullPointerException {
    return zip(
               e0s.iterator(), e1s.iterator(), e2s.iterator(), e3s.iterator(),
               e4s.iterator() );
  }

  /**
   * Zip five lists into a single list of T5s.
   *
   * @param   <E0>  Type of first list
   * @param   <E1>  Type of second list
   * @param   <E2>  Type of third list
   * @param   <E3>  Type of fourth list
   * @param   <E4>  Type of fifth list
   * @param   e0s   First elements; as an Iterator
   * @param   e1s   Second elements; as an Iterator
   * @param   e2s   Third elements; as an Iterator
   * @param   e3s   Fourth elements; as an Iterator
   * @param   e4s   Fifth elements; as an Iterator
   *
   * @return  Return an ArrayList of T5s, e0s go into T2s first element, e1s
   *          into the second, e2s into the third, e3s into the forth, and e4s
   *          into the fifth
   *
   * @throws  NullPointerException  Thrown if any of e0s, e1s, e2s, e3s, or e4s
   *                                is null
   */
  public static <E0, E1, E2, E3, E4> List<T5<E0, E1, E2, E3, E4>> zip(
                                                                      final Iterator<E0> e0s,
                                                                      final Iterator<E1> e1s,
                                                                      final Iterator<E2> e2s,
                                                                      final Iterator<E3> e3s,
                                                                      final Iterator<E4> e4s )
      throws NullPointerException {
    final ArrayList<T5<E0, E1, E2, E3, E4>> result =
      new ArrayList<T5<E0, E1, E2, E3, E4>>();
    while (
           e0s.hasNext() && e1s.hasNext() && e2s.hasNext() && e3s.hasNext() &&
               e4s.hasNext() ) {
      result.add(
                 t( e0s.next(), e1s.next(), e2s.next(), e3s.next(),
                    e4s.next() ) );
    }
    return result;
  }

  /**
   * Tuple of one element.
   *
   * @param  <E0>  Type of first element
   */
  public static class T1<E0> extends AbstractList<Object> {
    /** First element. */
    public final E0 e0;

    /**
     * Creates a new T1 tuple.
     *
     * @param  e0  Only element
     */
    public T1( final E0 e0 ) { this.e0 = e0; }

    /**
     * Returns the element at the given index, index must be 0.
     *
     * @param   index  Index of the element
     *
     * @return  Element at index
     *
     * @throws  IndexOutOfBoundsException  If index is not 0
     */
    public Object get( final int index ) throws IndexOutOfBoundsException {
      if ( index == 0 ) { return e0; }
      throw new IndexOutOfBoundsException( "index = " + index );
    }

    /**
     * Returns the size of the tuple - 1.
     *
     * @return  1
     */
    public int size() { return 1; }
  }

  /**
   * Tuple of two elements.
   *
   * @param  <E0>  Type of first element
   * @param  <E1>  Type of second element
   */
  public static class T2<E0, E1> extends T1<E0> {
    /** Second element. */
    public final E1 e1;

    /**
     * Creates a new T2 tuple.
     *
     * @param  e0  First element
     * @param  e1  Second element
     */
    public T2( final E0 e0, final E1 e1 ) {
      super( e0 );
      this.e1 = e1;
    }

    /**
     * Returns the element at the given index, index must be between 0 and 1
     * inclusive.
     *
     * @param   index  Index of the element
     *
     * @return  Element at index
     *
     * @throws  IndexOutOfBoundsException  If index is not between 0 and 1
     *                                     inclusive
     */
    @Override public Object get( final int index )
        throws IndexOutOfBoundsException {
      if ( index == 1 ) { return e1; }
      return super.get( index );
    }

    /**
     * Returns the size of the tuple - 2.
     *
     * @return  2
     */
    @Override public int size() { return 2; }

    /**
     * Returns the last element of the tuple inside a new tuple - t( e1 ).
     *
     * @return  t( e1 )
     */
    public T1<E1> tail() { return t( e1 ); }

    /**
     * Rotate (swap) the first two elements of the tuple and return them in a
     * new tuple - t( e1, e0 ).
     *
     * @return  t( e1, e0 )
     */
    public T2<E1, E0> rotate2() { return t( e1, e0 ); }
  }

  /**
   * Tuple of three elements.
   *
   * @param  <E0>  Type of first element
   * @param  <E1>  Type of second element
   * @param  <E2>  Type of third element
   */
  public static class T3<E0, E1, E2> extends T2<E0, E1> {
    /** Third element. */
    public final E2 e2;

    /**
     * Creates a new T3 tuple.
     *
     * @param  e0  First element
     * @param  e1  Second element
     * @param  e2  Third element
     */
    public T3( final E0 e0, final E1 e1, final E2 e2 ) {
      super( e0, e1 );
      this.e2 = e2;
    }

    /**
     * Returns the element at the given index, index must be between 0 and 2
     * inclusive.
     *
     * @param   index  Index of the element
     *
     * @return  Element at index
     *
     * @throws  IndexOutOfBoundsException  If index is not between 0 and 2
     *                                     inclusive
     */
    @Override public Object get( final int index )
        throws IndexOutOfBoundsException {
      if ( index == 2 ) { return e2; }
      return super.get( index );
    }

    /**
     * Returns the size of the tuple - 3.
     *
     * @return  3
     */
    @Override public int size() { return 3; }

    /**
     * Returns the last two elements of the tuple inside a new tuple - t( e1, e2
     * ).
     *
     * @return  t( e1, e2 )
     */
    @Override public T2<E1, E2> tail() { return t( e1, e2 ); }

    /**
     * Rotate the first three elements of the tuple and return them in a new
     * tuple - t( e2, e0, e1 ).
     *
     * @return  t( e2, e0, e1 )
     */
    public T3<E2, E0, E1> rotate3() { return t( e2, e0, e1 ); }
  }

  /**
   * Tuple of four elements.
   *
   * @param  <E0>  Type of first element
   * @param  <E1>  Type of second element
   * @param  <E2>  Type of third element
   * @param  <E3>  Type of fourth element
   */
  public static class T4<E0, E1, E2, E3> extends T3<E0, E1, E2> {
    /** Fourth element. */
    public final E3 e3;

    /**
     * Creates a new T4 tuple.
     *
     * @param  e0  First element
     * @param  e1  Second element
     * @param  e2  Third element
     * @param  e3  Forth element
     */
    public T4( final E0 e0, final E1 e1, final E2 e2, final E3 e3 ) {
      super( e0, e1, e2 );
      this.e3 = e3;
    }

    /**
     * Returns the element at the given index, index must be between 0 and 3
     * inclusive.
     *
     * @param   index  Index of the element
     *
     * @return  Element at index
     *
     * @throws  IndexOutOfBoundsException  If index is not between 0 and 3
     *                                     inclusive
     */
    @Override public Object get( final int index )
        throws IndexOutOfBoundsException {
      if ( index == 3 ) { return e3; }
      return super.get( index );
    }

    /**
     * Returns the size of the tuple - 4.
     *
     * @return  4
     */
    @Override public int size() { return 4; }

    /**
     * Returns the last three elements of the tuple inside a new tuple - t( e1,
     * e2, e3 ).
     *
     * @return  t( e1, e2, e3 )
     */
    @Override public T3<E1, E2, E3> tail() { return t( e1, e2, e3 ); }

    /**
     * Rotate the first four elements of the tuple and return them in a new
     * tuple - t( e3, e0, e1, e2 ).
     *
     * @return  t( e3, e0, e1, e2 )
     */
    public T4<E3, E0, E1, E2> rotate4() { return t( e3, e0, e1, e2 ); }
  }

  /**
   * Tuple of five elements.
   *
   * @param  <E0>  Type of first element
   * @param  <E1>  Type of second element
   * @param  <E2>  Type of third element
   * @param  <E3>  Type of fourth element
   * @param  <E4>  Type of fifth element
   */
  public static class T5<E0, E1, E2, E3, E4> extends T4<E0, E1, E2, E3> {
    /** Fifth element. */
    public final E4 e4;

    /**
     * Creates a new T5 tuple.
     *
     * @param  e0  First element
     * @param  e1  Second element
     * @param  e2  Third element
     * @param  e3  Forth element
     * @param  e4  Fifth element
     */
    public T5(
              final E0 e0, final E1 e1, final E2 e2, final E3 e3,
              final E4 e4 ) {
      super( e0, e1, e2, e3 );
      this.e4 = e4;
    }

    /**
     * Returns the element at the given index, index must be between 0 and 4
     * inclusive.
     *
     * @param   index  Index of the element
     *
     * @return  Element at index
     *
     * @throws  IndexOutOfBoundsException  If index is not between 0 and 4
     *                                     inclusive
     */
    @Override public Object get( final int index )
        throws IndexOutOfBoundsException {
      if ( index == 4 ) { return e4; }
      return super.get( index );
    }

    /**
     * Returns the size of the tuple - 5.
     *
     * @return  5
     */
    @Override public int size() { return 5; }

    /**
     * Returns the last four elements of the tuple inside a new tuple - t( e1,
     * e2, e3, e4 ).
     *
     * @return  t( e1, e2, e3, e4 )
     */
    @Override public T4<E1, E2, E3, E4> tail() { return t( e1, e2, e3, e4 ); }

    /**
     * Rotate the first five elements of the tuple and return them in a new
     * tuple - t( e4, e0, e1, e2, e3 ).
     *
     * @return  t( e4, e0, e1, e2, e3 )
     */
    public T5<E4, E0, E1, E2, E3> rotate5() { return t( e4, e0, e1, e2, e3 ); }
  }
}