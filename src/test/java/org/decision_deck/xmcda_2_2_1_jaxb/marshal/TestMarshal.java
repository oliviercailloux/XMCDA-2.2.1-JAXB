package org.decision_deck.xmcda_2_2_1_jaxb.marshal;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.decision_deck.xmcda_2_2_1_jaxb.ObjectFactory;
import org.decision_deck.xmcda_2_2_1_jaxb.X2Alternative;
import org.decision_deck.xmcda_2_2_1_jaxb.X2Alternatives;
import org.decision_deck.xmcda_2_2_1_jaxb.X2Criteria;
import org.decision_deck.xmcda_2_2_1_jaxb.X2Criterion;
import org.decision_deck.xmcda_2_2_1_jaxb.XMCDA;
import org.junit.Test;

public class TestMarshal {

	/**
	 * Let’s face it. This does not really test anything. It just documents
	 * usage.
	 */
	@Test
	public void marshalAndShow() throws JAXBException {
		final JAXBContext jc = JAXBContext.newInstance(XMCDA.class);
		final Marshaller marshaller = jc.createMarshaller();
		final ObjectFactory f = new ObjectFactory();

		final X2Alternatives alternatives = f.createX2Alternatives();
		final X2Alternative alt = f.createX2Alternative();
		alt.setId("a01");
		alternatives.getDescriptionOrAlternative().add(alt);

		final X2Criteria criteria = f.createX2Criteria();
		final X2Criterion crit = f.createX2Criterion();
		crit.setId("c01");
		crit.setMcdaConcept("concept");
		criteria.getCriterion().add(crit);

		final XMCDA xmcda = f.createXMCDA();
		final List<JAXBElement<?>> xmcdaSubElements = xmcda.getProjectReferenceOrMethodMessagesOrMethodParameters();
		xmcdaSubElements.add(f.createXMCDAAlternatives(alternatives));
		xmcdaSubElements.add(f.createXMCDACriteria(criteria));

		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(xmcda, System.out);
	}
}
