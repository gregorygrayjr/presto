/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prestosql.sql.planner.plan;

import io.prestosql.matching.Pattern;
import io.prestosql.matching.Property;
import io.prestosql.sql.planner.Symbol;
import io.prestosql.sql.tree.Expression;

import java.util.List;
import java.util.Optional;

import static io.prestosql.matching.Pattern.typeOf;
import static io.prestosql.matching.Property.optionalProperty;
import static io.prestosql.matching.Property.property;

public class Patterns
{
    private Patterns() {}

    public static Pattern<AssignUniqueId> assignUniqueId()
    {
        return typeOf(AssignUniqueId.class);
    }

    public static Pattern<AggregationNode> aggregation()
    {
        return typeOf(AggregationNode.class);
    }

    public static Pattern<ApplyNode> applyNode()
    {
        return typeOf(ApplyNode.class);
    }

    public static Pattern<DeleteNode> delete()
    {
        return typeOf(DeleteNode.class);
    }

    public static Pattern<ExchangeNode> exchange()
    {
        return typeOf(ExchangeNode.class);
    }

    public static Pattern<EnforceSingleRowNode> enforceSingleRow()
    {
        return typeOf(EnforceSingleRowNode.class);
    }

    public static Pattern<FilterNode> filter()
    {
        return typeOf(FilterNode.class);
    }

    public static Pattern<IndexSourceNode> indexSource()
    {
        return typeOf(IndexSourceNode.class);
    }

    public static Pattern<JoinNode> join()
    {
        return typeOf(JoinNode.class);
    }

    public static Pattern<SpatialJoinNode> spatialJoin()
    {
        return typeOf(SpatialJoinNode.class);
    }

    public static Pattern<LateralJoinNode> lateralJoin()
    {
        return typeOf(LateralJoinNode.class);
    }

    public static Pattern<LimitNode> limit()
    {
        return typeOf(LimitNode.class);
    }

    public static Pattern<MarkDistinctNode> markDistinct()
    {
        return typeOf(MarkDistinctNode.class);
    }

    public static Pattern<OutputNode> output()
    {
        return typeOf(OutputNode.class);
    }

    public static Pattern<ProjectNode> project()
    {
        return typeOf(ProjectNode.class);
    }

    public static Pattern<SampleNode> sample()
    {
        return typeOf(SampleNode.class);
    }

    public static Pattern<SemiJoinNode> semiJoin()
    {
        return typeOf(SemiJoinNode.class);
    }

    public static Pattern<SortNode> sort()
    {
        return typeOf(SortNode.class);
    }

    public static Pattern<TableFinishNode> tableFinish()
    {
        return typeOf(TableFinishNode.class);
    }

    public static Pattern<TableScanNode> tableScan()
    {
        return typeOf(TableScanNode.class);
    }

    public static Pattern<TableWriterNode> tableWriterNode()
    {
        return typeOf(TableWriterNode.class);
    }

    public static Pattern<TopNNode> topN()
    {
        return typeOf(TopNNode.class);
    }

    public static Pattern<UnionNode> union()
    {
        return typeOf(UnionNode.class);
    }

    public static Pattern<ValuesNode> values()
    {
        return typeOf(ValuesNode.class);
    }

    public static Pattern<WindowNode> window()
    {
        return typeOf(WindowNode.class);
    }

    public static Pattern<RowNumberNode> rowNumber()
    {
        return typeOf(RowNumberNode.class);
    }

    public static <C> Property<PlanNode, C, PlanNode> source()
    {
        return optionalProperty("source", node -> node.getSources().size() == 1 ?
                Optional.of(node.getSources().get(0)) :
                Optional.empty());
    }

    public static <C> Property<PlanNode, C, List<PlanNode>> sources()
    {
        return property("sources", PlanNode::getSources);
    }

    public static class Aggregation
    {
        public static <C> Property<AggregationNode, C, List<Symbol>> groupingColumns()
        {
            return property("groupingKeys", AggregationNode::getGroupingKeys);
        }

        public static <C> Property<AggregationNode, C, AggregationNode.Step> step()
        {
            return property("step", AggregationNode::getStep);
        }
    }

    public static class Apply
    {
        public static <C> Property<ApplyNode, C, List<Symbol>> correlation()
        {
            return property("correlation", ApplyNode::getCorrelation);
        }
    }

    public static class Exchange
    {
        public static Property<ExchangeNode, ExchangeNode.Scope> scope()
        {
            return property("scope", ExchangeNode::getScope);
        }
    }

    public static class LateralJoin
    {
        public static <C> Property<LateralJoinNode, C, List<Symbol>> correlation()
        {
            return property("correlation", LateralJoinNode::getCorrelation);
        }

        public static Property<LateralJoinNode, PlanNode> subquery()
        {
            return property("subquery", LateralJoinNode::getSubquery);
        }
    }

    public static class Limit
    {
        public static <C> Property<LimitNode, C, Long> count()
        {
            return property("count", LimitNode::getCount);
        }
    }

    public static class Sample
    {
        public static <C> Property<SampleNode, C, Double> sampleRatio()
        {
            return property("sampleRatio", SampleNode::getSampleRatio);
        }

        public static <C> Property<SampleNode, C, SampleNode.Type> sampleType()
        {
            return property("sampleType", SampleNode::getSampleType);
        }
    }

    public static class TopN
    {
        public static <C> Property<TopNNode, C, TopNNode.Step> step()
        {
            return property("step", TopNNode::getStep);
        }
    }

    public static class Values
    {
        public static <C> Property<ValuesNode, C, List<List<Expression>>> rows()
        {
            return property("rows", ValuesNode::getRows);
        }
    }
}