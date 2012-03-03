package sm_mm.diagram.layout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import mockit.Mocked;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;

import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.GraphicalEditPart;
import org.junit.Test;
import org.junit.runner.RunWith;

import sm_mm.Operator;
import sm_mm.diagram.edit.parts.A2AEditPart;
import sm_mm.diagram.edit.parts.A2CEditPart;
import sm_mm.diagram.edit.parts.C2CEditPart;
import sm_mm.diagram.edit.parts.ClassEditPart;

@RunWith(JMockit.class)
public class TContainerPartLayouter {
	@Mocked
	private GraphicalEditPart containerEditPart;
	@Mocked
	private IFigure figure;
	
	@Mocked
	private ClassEditPart lPersonShape;
	@Mocked
	private ClassEditPart rPersonShape;
	@Mocked
	private ClassEditPart rFamilyShape;
	
	@Mocked
	private C2CEditPart person2personShape;
	@Mocked
	private A2AEditPart fn2fnShape;
	@Mocked
	private A2CEditPart a2cShape;
	
	@Test
	public void testLayouterOnPersonFamilyModel() {
		PersonFamilyModel pfm = new PersonFamilyModel();
		
		BoxBuilder bbuilder = new BoxBuilder();
		
		Set<Operator> ops = new HashSet<Operator>(
				Arrays.asList(pfm.person2person, pfm.fn2fn, pfm.a2c));

		List<Box> boxes = bbuilder.build(ops);
		
		BoxSorter bsorter = new BoxSorterImpl();
		bsorter.setBoxes(boxes);

		List<Box> sorted = bsorter.getSorted();
		
		new NonStrictExpectations() {
			{
				containerEditPart.getContentPane();returns(figure);
				figure.getBounds();returns(new Rectangle(0,0,300,300));
				
				onInstance(person2personShape).getSize();returns(new Dimension(20,30));
				onInstance(person2personShape).getModel();returns("person2person");
				
				onInstance(fn2fnShape).getSize();returns(new Dimension(20,30));
				onInstance(fn2fnShape).getModel();returns("fn2fn");
				
				onInstance(a2cShape).getSize();returns(new Dimension(20,30));
				onInstance(a2cShape).getModel();returns("a2c");
				
				onInstance(lPersonShape).getSize();returns(new Dimension(20,30));
				onInstance(lPersonShape).getModel();returns("lPerson");
				onInstance(rPersonShape).getSize();returns(new Dimension(20,30));
				onInstance(rPersonShape).getModel();returns("rPerson");
				onInstance(rFamilyShape).getSize();returns(new Dimension(20,30));
				onInstance(rFamilyShape).getModel();returns("rFamily");
				
			}
		};
		
		ModelManager mmanager = new ModelManager();
		mmanager.addShape(pfm.lPerson, lPersonShape);
		mmanager.addShape(pfm.rPerson, rPersonShape);
		mmanager.addShape(pfm.rFamily, rFamilyShape);
		mmanager.addShape(pfm.person2person, person2personShape);
		mmanager.addShape(pfm.fn2fn, fn2fnShape);
		mmanager.addShape(pfm.a2c, a2cShape);
		
		ContainerPartLayouter cpl = new ContainerPartLayouter(mmanager);
		LayoutContainerPart lcp = cpl.layout(containerEditPart, sorted);
		
		for(LayoutBoxPart lb : lcp.getLayoutBoxes()) {
			System.out.println(lb);
		}
		
		// TODO: sensible layout tests
		
	}
}
