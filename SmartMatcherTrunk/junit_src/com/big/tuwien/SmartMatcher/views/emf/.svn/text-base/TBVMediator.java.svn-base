package com.big.tuwien.SmartMatcher.views.emf;

import mockit.Mocked;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

import org.apache.log4j.xml.DOMConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.big.tuwien.ModelManager.ExampleStore;
import com.big.tuwien.ModelManager.imodel.ModelParsingException;
import com.big.tuwien.SmartMatcher.mmodel.ResourceMetaModelFactory;
import com.big.tuwien.SmartMatcher.model.ModelManager;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C;
import com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2CConfiguration;
import com.big.tuwien.SmartMatcher.views.bubble.Bubble;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleView;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewException;
import com.big.tuwien.SmartMatcher.views.bubble.BubbleViewListener;

@RunWith(JMockit.class)
public class TBVMediator {
	protected ModelManager modelManager;
	protected BubbleView bubbleView;
	protected ResourceMetaModelFactory mmFactory;
	
	@Mocked 
	protected BubbleViewListener listenerMock;
	
	
	public TBVMediator() {
		DOMConfigurator.configure("junit_log4j.xml");
	}
	
	
	@Before
	public void setUp() throws BubbleViewException, ModelParsingException {
		modelManager = new ModelManager();
		modelManager.setExample(ExampleStore.PERSON_PERSONFAMILY);
		modelManager.init();
		
		mmFactory = new ResourceMetaModelFactory();
		mmFactory.setLHSResource(modelManager.getInputMetaModel());
		mmFactory.setRHSResource(modelManager.getOutputMetaModel());
		mmFactory.build();
		
		bubbleView = new BubbleView();
		bubbleView.setMetaModelFactory(mmFactory);
		bubbleView.buildView();
	}
	
	
	@Test
	public void testInternalBubbleViewEventPropagation() {
		BVMediator bvMediator = new EventFilteredBVMediator(bubbleView);
		bvMediator.addListener(listenerMock);
		
		@SuppressWarnings("unchecked")
		final Bubble<C2C> bubble = 
			(Bubble<com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C>)
						bubbleView.getBubbleFactory().createBubbleInstance(
								com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C.class);
		
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setLHSFocusElement(mmFactory.getLHSMetaModel().getClassByName("Person"));
		c2cConfig.setRHSFocusElement(mmFactory.getRHSMetaModel().getClassByName("Family"));
		
		bubble.setConfiguration(c2cConfig);
		
		// add bubble -> FILTERED
		bvMediator.addBubble(bubble);
		
		// change bubble state -> FILTERED
		bvMediator.changeState(bubble, Bubble.STATE.eval2FALSE);
		
		new Verifications() {
			{
				// the events generated through adding or changing a bubble INTERNALLY
				// are filtered -> registered listeners are not notified
				listenerMock.bubbleViewEvent(withAny((int) 1),  (Bubble<?>) withNotNull());repeats(0);
			}
		};
	}
	
	
	@Test
	public void testExternalBubbleViewEventPropagation() {
		BVMediator bvMediator = new EventFilteredBVMediator(bubbleView);
		bvMediator.addListener(listenerMock);
		
		@SuppressWarnings("unchecked")
		final Bubble<C2C> bubble = 
			(Bubble<com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C>)
						bubbleView.getBubbleFactory().createBubbleInstance(
								com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C.class);
		
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setLHSFocusElement(mmFactory.getLHSMetaModel().getClassByName("Person"));
		c2cConfig.setRHSFocusElement(mmFactory.getRHSMetaModel().getClassByName("Family"));
		
		bubble.setConfiguration(c2cConfig);
		
		// add bubble to BubbleView (= EXTERNALLY) -> NOT FILTERED
		bubbleView.addCurrentLevelBubble(bubble);
		
		// EXTERNALLY change bubble state -> NOT FILTERED
		bubble.setState(Bubble.STATE.eval2FALSE);
		
		new Verifications() {
			{
				// 2 notifications about EXTERNAL EVENTS
				listenerMock.bubbleViewEvent(withAny((int) 1),  (Bubble<?>) withNotNull());repeats(2);
			}
		};
	}
	
	
	@Test
	public void testMixedBubbleViewEventPropagation() {
		BVMediator bvMediator = new EventFilteredBVMediator(bubbleView);
		bvMediator.addListener(listenerMock);
		
		@SuppressWarnings("unchecked")
		final Bubble<C2C> bubble = 
			(Bubble<com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C>)
						bubbleView.getBubbleFactory().createBubbleInstance(
								com.big.tuwien.SmartMatcher.operators.homogenic.c2c.C2C.class);
		
		C2CConfiguration c2cConfig = new C2CConfiguration();
		c2cConfig.setLHSFocusElement(mmFactory.getLHSMetaModel().getClassByName("Person"));
		c2cConfig.setRHSFocusElement(mmFactory.getRHSMetaModel().getClassByName("Family"));
		
		bubble.setConfiguration(c2cConfig);
		
		// add bubble to BubbleView (= EXTERNALLY) -> NOT FILTERED
		bubbleView.addCurrentLevelBubble(bubble);
		
		// INTERNALLY change bubble state -> FILTERED
		bvMediator.changeState(bubble, Bubble.STATE.eval2TRUE);
		
		// EXTERNALLY change bubble state -> NOT FILTERED
		bubble.setState(Bubble.STATE.eval2FALSE);
		
		new Verifications() {
			{
				// 2 notifications about EXTERNAL EVENTS
				listenerMock.bubbleViewEvent(withAny((int) 1),  (Bubble<?>) withNotNull());repeats(2);
			}
		};
	}
	
}
