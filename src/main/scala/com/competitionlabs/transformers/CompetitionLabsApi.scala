/***
  *  Copyright (C) Competition Labs Ltd - All Rights Reserved
  *  Unauthorized copying of this file, via any medium is strictly prohibited
  *  Proprietary and confidential
  *  Written by Competition Labs Ltd, 2019
  */
package com.competitionlabs.transformers

import java.nio.charset.Charset

import com.competitionlabs.transformers.domain._
import org.joda.time.DateTime
import org.json4s.JsonAST.JValue

trait CompetitionLabsApi extends CompetitionLabsApiHttp {

	/***
	  * Generate a unique time based UUID, this can be used to set the batchId value if
	  * a single event is transformed into multiple distinct events (facts) and a correlation
	  * needs to be maintained
	  * @return A time based UUID as a string
	  */
	def nextId: String

	/**
	  * Your account identifier
	  */
	def accountId: String

	/**
	  * Get the space name associated with this account
	  */
	def spaceName: String

	/**
	  * Get sub accounts for this master account if any exists
	  */
	def subAccounts: Map[String, CompetitionLabsApi]
	
	def getSubAccount(spaceName: String): Option[CompetitionLabsApi]

	/**
	  * Insert an event into your CompetitionLabs space
	  * @param event The event to add
	  * @return True on success, false on duplicate and exception if malformed
	  */
	def pushEvent(event: BasicEventModel): Boolean

	/**
	  * Insert a sequence of events into your CompetitionLabs space
	  * @param events The events to add
	  * @return True on success, false on duplicate and exception if malformed
	  */
	def pushEvents(events: Seq[BasicEventModel]): Boolean

	/**
	  * Insert an event into your CompetitionLabs space
	  * @param event The event to add
	  * @param delay The time in milliseconds to delay processing of event
	  * @return True on success, false on duplicate and exception if malformed
	  */
	def pushEventWithDelay(event: BasicEventModel, delay:Long): Boolean

	/**
	  * Insert a sequence of events into your CompetitionLabs space
	  * @param events The events to add
	  * @param delay The time in milliseconds to delay processing of event
	  * @return True on success, false on duplicate and exception if malformed
	  */
	def pushEventsWithDelay(events: Seq[BasicEventModel], delay:Long): Boolean

	/**
	  * Get the CompetitionLabs id for the member based on your reference id
	  * @param memberReferenceId The id used to identify this member in the sending system
	  * @return The id used in the CompetitionLabs system or None if the user does not exist
	  */
	def memberIdFromMemberRefId(memberReferenceId: String): Option[String]

	/**
	  * Get the member reference id for the member based on CompetitionLabs id
	  * @param memberId The id used to identify this member in the sending system
	  * @return The id used in the CompetitionLabs system or None if the user does not exist
	  */
	def memberRefIdFromMemberId(memberId: String): Option[String]

	/**
	  * Create a member in the CompetitionLabs system
	  * @param memberReferenceId The id used to identify this member in the sending system
	  * @param displayName Display name
	  * @param groups The groups to add this member to
	  * @return The id used in the CompetitionLabs system
	  */
	def createMember(memberReferenceId: String, displayName: String, groups:Seq[String], metaData: Option[Map[String, String]] = None): Option[String]

	/**
	  *
	  * @param clMemberId CL Member Id
	  * @param displayName Display name
	  * @param groupsToUpdate The groups to add this member to
	  * @return The id used in the CompetitionLabs system
	  */
	def updateMember(clMemberId: String, memberReferenceId: Option[String], displayName: Option[String], groupsToUpdate:Option[Array[String]], metaData: Option[Map[String, String]]): Option[String]

	/**
	  *
	  * @param memberId CompetitionLabs Reward Id
	  * @return BasicMemberModel returns a basic member object
	  */
	def getMember(memberId: String): Option[BasicMemberModel]

	/**
	  * Get the CompetitionLabs id for the product based on your reference id
	  * @param productReferenceId The id used to identify this product in the sending system
	  * @return The id used in the CompetitionLabs system or None if the product does not exist
	  */
	def productIdFromProductRefId(productReferenceId: String): Option[String]

	/**
	  * Get the product id for the product based on your CompetitionLabs id
	  * @param productId The id used to identify this product in the sending system
	  * @return The id used in the CompetitionLabs system or None if the product does not exist
	  */
	def productRefIdFromProductId(productId: String): Option[String]

	/**
	  *
	  * @param productReferenceId The id used to identify this product in the sending system
	  * @param displayName Display name
	  * @param providers The providers of this product
	  * @param productType The type of product
	  * @param defaultAdjustmentFactor The default adjustment factor to apply
	  * @return The id used in the CompetitionLabs system
	  */
	def createProduct(productReferenceId: String, displayName: String, providers:Seq[String], productType: String, defaultAdjustmentFactor: Double, metaData: Option[Map[String, String]] = None): Option[String]

	/**
	  *
	  * @param clProductId CL Product Id
	  * @param displayName Display name
	  * @param providers The providers of this product
	  * @param productType The type of product
	  * @param defaultAdjustmentFactor The default adjustment factor to apply
	  * @return The id used in the CompetitionLabs system
	  */
	def updateProduct(clProductId: String, productReferenceId: Option[String], displayName: Option[String], providers:Option[Array[String]], productType: Option[String], defaultAdjustmentFactor: Option[Double], metaData: Option[Map[String, String]]): Option[String]

	/**
	  *
	  * @param productId CompetitionLabs Product Id
	  * @return BasicProductModel returns a basic product object
	  */
	def getProduct(productId: String): Option[BasicProductModel]

	/**
	  * Verify if the event action type exists in your space
	  * @param action The action
	  * @return True of the action was created
	  */
	def eventActionExists(action: String): Boolean

	/***
	  * Create the action in your space
	  * @param action True on success false on failure
	  * @return
	  */
	def createEventAction(action: String, name: Option[String] = None, metaData: Option[Map[String, String]] = None): Boolean

	/***
	  * Update the action in your space
	  * @param action True on success false on failure
	  * @return
	  */
	def updateEventAction(action: String, name: Option[String] = None, metaData: Option[Map[String, String]] = None, unitOfMeasureType: Option[String]= None): Boolean
	
	/**
	  * [PROTOTYPE]
	  * Get the spot rate for a currency at a point in time from Oanda
	  * @param fromCurrency From currency ISO3 code
	  * @param toCurrency To currency ISO3 code
	  * @return The rate
	  */
	def spotExchangeRate(fromCurrency: String, toCurrency: String, pointInTime: DateTime): Double

	/**
	  *
	  * @param achievementId CompetitionLabs Achievement Id
	  * @return BasicAchievementModel returns a basic achievement object
	  */
	def getAchievement(achievementId: String): Option[BasicAchievementModel]

	/**
	  *
	  * @param contestId CompetitionLabs Contest Id
	  * @return BasicContestModel returns a basic contest object
	  */
	def getContest(contestId: String): Option[BasicContestModel]

	/**
	  *
	  * @param rewardId CompetitionLabs Reward Id
	  * @return BasicRewardModel returns a basic reward object
	  */
	def getReward(rewardId: String): Option[BasicRewardModel]

	/**
	  *
	  * @param awardId CompetitionLabs Award Id
	  * @return BasicAwardModel returns a basic award object
	  */
	def getAward(awardId: String): Option[BasicAwardModel]

	/**
	  *
	  * @param unitOfMeasureId CompetitionLabs Unit of Measure Id
	  * @return BasicUnitOfMeasureModel returns a basic unit of measure object
	  */
	def getUnitOfMeasure(unitOfMeasureId: String): Option[BasicUnitOfMeasureModel]

	/**
	  *
	  * @param unitOfMeasureKey CompetitionLabs UoM key
	  * @return Double returns a multiplier associated with the UoM
	  */
	def getUoMMultiplierFromKey(unitOfMeasureKey: String): Option[Double]

	/**
	  * Converts a json string to a JValue
	  * @param body The string to deserialise
	  * @return JValue or throws exception
	  */
	def fromJsonString(body: String): JValue

	/**
	  * Converts a map to a json string
	  * @param obj The object to serialise
	  * @return json string or throws exception
	  */
	def toJsonFromMap(obj: Map[String, Any]): String

	/**
	  * Converts byte array to String using UTF-8
	  * @param body The string encoded as bytes
	  * @param charset Optional, character set to decode byte array, default is UTF-8
	  * @return Decoded string or throws exception
	  */
	def convertByteArrayToString(body: Array[Byte], charset: String = "UTF-8"): String = new String(body, Charset.forName(charset))

}
