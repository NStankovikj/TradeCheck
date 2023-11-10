### First idea

Checks belong to a Trade.
- Subchecks belong to a Check.
- CheckInvestigations indicate who investigated a Check and when.
- SubcheckInvestigations belong to a CheckInvestigation and Subcheck
and indicate which of the Subchecks were OK/NOK.
- InvestigationComments can optionally be added to specific
SubcheckInvestigations to explain why a certain Subcheck was OK/NOK.

From this, the checks and trade are looking okay. Investigation comments are clearly in a one to many relationship with subcheckInvestigations.
My difficulties were with Subchecks, CheckInvestigations and SubcheckInvestigations. 
In one ERD i went with subcheckInvestigations being only connected to subchecks via manyToOne and the same way with checks and checkInvestigations. This poses a problem with the requirement to group comments by Investigator which is part of the CheckInvestigation
but the comments are connected with SubCheckInvestigation. Also from a business logic point of view it looks like the names for CheckInvestigation and subCheckInvestigation are a bit confusing.
<img width="1169" alt="Screenshot 2023-11-10 at 05 29 51" src="https://github.com/NStankovikj/TradeCheck/assets/19278351/a4579193-ff98-4b08-8794-a4eeb24a36d9">

### Second idea

CheckInvestigation states who started an investigation on a check but then  subcheck investigation has the actual outcome of this investigation (name).
My approach is the following:
A subcheck can have one investigator and one outcome or, 5 investigators and 5 outcomes, but never
more outcomes than investigators. So i would say 
subcheck to checkInvestigation relationship is one to many 
and checkinvestigation to subcheckInvestigation is one to one. 
The logic here would be to have an endpoint
 startInvestigation, which creates a checkInvestigation 
and an endpoint endInvestigation which updates the checkInvestigation by id and adds and end timestamp ( start timestamp is created on checkInvestigation creation ) and also creates the subcheckInvestigation.
an additional thought is there there can be a check without subchecks, so a check can have multiple checkInvestigations and subcheckInvestigations via checkInvestigations
an investigation comment can only be added when there is a subcheck investigation
and maybe the person wants to add additional comments after doing the subcheck investigation, so the relationship should be one to many
Showing all comments of all investigations of a check (grouped by the investigation
they belong to) <- this also points that multiple comments can be on subcheck investigations. and if there are multiple checkInvestigations, there will be multiple subcheckinvestigations (this was decided to be 1 to 1 ) meaning it is still possible to have multiple comments by one person and multiple by a different one ( group by )

the last requirement for deleting subchecks under the short time i would just make it that it deletes the child objects (orphan removal annotation) but a better design would be not to use hard delete but to use soft delete instead, where all checks comments and actions by people are present in the DB just marked as deleted/inactive
<img width="1300" alt="Screenshot 2023-11-10 at 03 37 53" src="https://github.com/NStankovikj/TradeCheck/assets/19278351/41cd9ff5-9950-4d34-b7ec-edfa3fbdf4f1">

